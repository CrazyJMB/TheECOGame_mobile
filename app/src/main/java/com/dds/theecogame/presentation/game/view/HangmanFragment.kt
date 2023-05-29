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
        gameViewModel.addBtnPressed('_')

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
                    println("HANGMAN: " + game.challengesList[gameViewModel.getQuestionNumber()])
                    currentHangman = nextQuestion.hangmanModel

                    gameViewModel.currentChallengeClue = currentHangman.clue

                    changeViewImage(currentHangman.ods)

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
            if (!it.equals('_')) {
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

        mistakes = 0
        userMistake()
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
            0 -> {
                binding.ivHead.visibility = View.INVISIBLE;
                binding.ivBody.visibility = View.INVISIBLE;
                binding.ivLeftArm.visibility = View.INVISIBLE;
                binding.ivRightArm.visibility = View.INVISIBLE;
                binding.ivLeftLeg.visibility = View.INVISIBLE;
            }

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

    private val odsDrawableMap = mapOf(
        1 to R.drawable.ods1,
        2 to R.drawable.ods2,
        3 to R.drawable.ods3,
        4 to R.drawable.ods4,
        5 to R.drawable.ods5,
        6 to R.drawable.ods6,
        7 to R.drawable.ods7,
        8 to R.drawable.ods8,
        9 to R.drawable.ods9,
        10 to R.drawable.ods10,
        11 to R.drawable.ods11,
        12 to R.drawable.ods12,
        13 to R.drawable.ods13,
        14 to R.drawable.ods14,
        15 to R.drawable.ods15,
        16 to R.drawable.ods16,
        17 to R.drawable.ods17
    )

    private fun changeViewImage(ods: Int) {
        val resourceId = odsDrawableMap[ods] ?: R.drawable.ods1
        binding.ivODSHangman.setImageResource(resourceId)
    }

    override fun onStop() {
        super.onStop()
        if (tense) {
            mediaPlayer.stop()
        }
    }
}