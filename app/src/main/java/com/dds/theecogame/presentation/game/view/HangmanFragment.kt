package com.dds.theecogame.presentation.game.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentHangmanBinding
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.model.challenges.Hangman
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HangmanFragment : Fragment() {

    private lateinit var binding: FragmentHangmanBinding
    private lateinit var mediaPlayer: MediaPlayer
    private val gameViewModel: GameViewModel by activityViewModels()

    private var countDownTimer: CountDownTimer? = null
    private var timerCancelledManually: Boolean = false
    private var tense: Boolean = false

    private var mistakes = 0
    private var word = ""
    private var dificulty = 0
    private var listNotMissingChar = mutableListOf<Char>()
    private var listMissingChar = mutableListOf<Char>()
    private lateinit var currentHangman: Hangman

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHangmanBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameViewModel.setInFragmentChallenges(true)

        binding.ivPointsHangman.setOnClickListener {
            val builder =
                AlertDialog.Builder(ContextThemeWrapper(requireContext(), R.style.alert_style))
            builder.setTitle(R.string.alert_points)
            builder.setMessage(
                getString(R.string.total_points) + " " +
                        gameViewModel.getPoints().toString() +
                        "\n\n" +
                        getString(R.string.consolidate_points) + " " +
                        gameViewModel.getConsolidatedPoints().toString()
            )
            builder.setPositiveButton(R.string.alert_confirm) { _, _ ->
                //No hace nada
            }
            val alertDialog = builder.create()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
        }

        gameViewModel.gameLiveData.observe(viewLifecycleOwner) { game ->
            when (val nextQuestion = game.challengesList[gameViewModel.getQuestionNumber()]) {
                is Game.Challenge.HangmanModel -> {
                    println("Question: " + game.challengesList)
                    currentHangman = nextQuestion.hangmanModel

                    gameViewModel.currentChallengeClue = currentHangman.clue

                    lifecycleScope.launch(Dispatchers.IO) {
                        gameViewModel.registerChallenge(currentHangman.id, "HANGMAN")
                    }

                    word = currentHangman.word.uppercase()
                    dificulty = currentHangman.difficulty

                    makeWordDificulty()

                    binding.tvPointsNumberHangman.text = (dificulty * 10).toString()
                    binding.tvHangmanNumber.text = gameViewModel.getQuestionNumber().toString()
                }
            }
        }

        gameViewModel.countdownLiveData.observe(viewLifecycleOwner) {
            binding.tvTimerHangman.text = it.toString()
            if (it.toInt() == 10 && !timerCancelledManually) {
                playTenseMusic()
                tense = true
            }

            if (it.toInt() == 0) {
                if (!timerCancelledManually) {
                    mediaPlayer.stop()
                    playLosingMusic(false)
                    goToSummary()
                }
            }
        }

        startTimer()

        gameViewModel.btnPressed.observe(viewLifecycleOwner) {
            if (listMissingChar.contains(it)) {
                var listIndexChange: MutableList<Int> = mutableListOf()
                var hangmanWord = binding.tvHangmanWord.text.toString().toMutableList()

                for ((index, char) in word.withIndex()) {
                    if (char == it) {
                        listIndexChange.add(index)
                    }
                }

                for (index in listIndexChange) {
                    hangmanWord[index] = it
                }

                binding.tvHangmanWord.text = hangmanWord.joinToString("")

                if (binding.tvHangmanWord.text.equals(word)) {

                    lifecycleScope.launch(Dispatchers.IO) { gameViewModel.registerHangmanCorrect() }

                    gameViewModel.nextQuestionNumber()

                    if (gameViewModel.getUsedHelp()) {
                        gameViewModel.addPoints((dificulty * 10) / 2)
                        gameViewModel.setUsedHelp(false)
                    } else {
                        gameViewModel.addPoints(dificulty * 10)
                    }

                    if (gameViewModel.getQuestionNumber() == 11) {
                        stopTimer()
                        goToSummary()
                    } else if (gameViewModel.getConsolidated()) {
                        stopTimer()
                        goToAbandon()
                    } else {
                        stopTimer()
                        goToConsolidate()
                    }
                }
            } else {
                mistakes++
                userMistake()
            }
        }

    }

    private fun makeWordDificulty() {
        //1 -> 50%, 2 -> 40%, 3 -> 30%, 4-> 20%, 5-> 10%
        when (dificulty) {
            1 -> perecentageNoCharacters(0.5)
            2 -> perecentageNoCharacters(0.6)
            3 -> perecentageNoCharacters(0.7)
            4 -> perecentageNoCharacters(0.8)
            else -> perecentageNoCharacters(0.9)
        }
    }

    private fun perecentageNoCharacters(perectange: Double) {
        val numberWords = (word.length * perectange).toInt()
        val characters = word.substring(0, numberWords)
        var i = 0

        while (i < characters.length - 1) {
            if (!listMissingChar.contains(characters.elementAt(i))) {
                listMissingChar.add(characters.elementAt(i))
            }
            i++
        }

        val auxiliar = word.toList().toMutableList()
        listMissingChar.forEach { element ->
            if (auxiliar.contains(element)) {
                val indices = auxiliar.withIndex().filter {
                    it.value == element &&
                            it.value != 'Ñ' && it.value != 'Ú' &&
                            it.value != ' '
                }.map { it.index }
                indices.forEach { index ->
                    auxiliar[index] = '_'
                }
            }
        }

        binding.tvHangmanWord.text = auxiliar.joinToString("")

        var wordList = word.toList().toMutableList()
        wordList.retainAll { auxiliar.contains(it) }
        wordList.toSet().toMutableList()

        listNotMissingChar.addAll(wordList)
        gameViewModel.changeStartingVisibilityLetters(listNotMissingChar)
    }

    private fun userMistake() {
        when (mistakes) {
            1 -> {
                binding.ivHead.visibility = View.VISIBLE; playLosingMusic(true)
            }

            2 -> {
                binding.ivBody.visibility = View.VISIBLE; playLosingMusic(true)
            }

            3 -> {
                binding.ivLeftArm.visibility = View.VISIBLE; playLosingMusic(true)
            }

            4 -> {
                binding.ivRightArm.visibility = View.VISIBLE; playLosingMusic(true)
            }

            5 -> {
                binding.ivLeftLeg.visibility = View.VISIBLE; playLosingMusic(true)
            }

            else -> {

                lifecycleScope.launch(Dispatchers.IO) { gameViewModel.registerHangmanFailed() }

                binding.ivRightLeg.visibility = View.VISIBLE; playLosingMusic(true)
                stopTimer()
                if (tense) {
                    mediaPlayer.stop()
                }
                playLosingMusic(false)
                gameViewModel.setGameStatus(0)
                goToSummary()
            }
        }
    }

    private fun startTimer() {
        gameViewModel.startCountDownTimer(120 * 1000)
    }

    private fun stopTimer() {
        gameViewModel.stopCountDownTimer()
        timerCancelledManually = true
        countDownTimer = null
    }

    private fun playLosingMusic(firstTime: Boolean) {
        if (firstTime) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.fallo)
        } else {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.perder)
        }
        mediaPlayer.isLooping = false
        mediaPlayer.start()
    }

    private fun playTenseMusic() {
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.tensa)
        mediaPlayer.isLooping = false
        mediaPlayer.start()
    }

    private fun goToConsolidate() {
        gameViewModel.setInFragmentChallenges(false)
        val consolidateFragment = ConsolidateFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, consolidateFragment)
            .commit()
    }

    private fun goToSummary() {
        gameViewModel.setInFragmentChallenges(false)
        val summaryFragment = ResumeFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, summaryFragment)
            .commit()
    }

    private fun goToAbandon() {
        gameViewModel.setInFragmentChallenges(false)
        val abandonFragment = AbandonFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, abandonFragment)
            .commit()
    }

    override fun onStop() {
        super.onStop()
        if (tense) {
            mediaPlayer.stop()
        }
    }
}