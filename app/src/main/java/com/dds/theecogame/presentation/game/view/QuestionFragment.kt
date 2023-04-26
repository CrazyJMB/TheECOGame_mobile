package com.dds.theecogame.presentation.game.view

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
//import androidx.databinding.ObservableInt
import com.dds.theecogame.R


import com.dds.theecogame.databinding.FragmentQuestionsBinding
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.model.Question
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.game.viewModel.QuestionViewModel
import kotlin.concurrent.timer

class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var mediaPlayer: MediaPlayer

    private val viewModel: QuestionViewModel by viewModels()
    private val gameViewModel: GameViewModel by activityViewModels()

    private var countDownTimer: CountDownTimer? = null
    private var timerCancelledManually: Boolean = false
    private var tense: Boolean = false
    private lateinit var currentQuestion: Question

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionsBinding.inflate(inflater)
        viewModel.setSharedViewModel(gameViewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set question information
        gameViewModel.gameLiveData.observe(viewLifecycleOwner) { game ->
            when (val nextQuestion = game.deleteFirstChallenge()) {
                is Game.Challenge.QuestionModel -> {
                    currentQuestion = nextQuestion.questionModel

                    nextQuestion.let {
                        val questionList = listOf(
                            it.questionModel.option1,
                            it.questionModel.option2,
                            it.questionModel.option3,
                            it.questionModel.answer
                        ).shuffled()

                        binding.tvQuestion.text = it.questionModel.question
                        binding.rbOptionA.text = questionList[0]
                        binding.rbOptionB.text = questionList[1]
                        binding.rbOptionC.text = questionList[2]
                        binding.rbOptionD.text = questionList[3]
                    }
                }
            }
        }

        startTimer()
        binding.tvQuestionNumber.text = gameViewModel.getQuestionNumber().toString()
        changeViewImage()

        binding.btnContinue.setOnClickListener {
            val correctAnswer = currentQuestion.answer
            val isCorrect = checkAnswer(correctAnswer)
            stopTimer()
            if (isCorrect) {
                val points = 10 * currentQuestion.difficulty
                if (gameViewModel.getSecondChance()) {
                    gameViewModel.addPoints(points / 2)
                } else {
                    gameViewModel.addPoints(points)
                }
                if (tense) {
                    mediaPlayer.stop()
                }

                gameViewModel.nextQuestionNumber()
                if (gameViewModel.getQuestionNumber() == 11) {
                    goToSummary()
                } else if (gameViewModel.getConsolidated() == false){
                    goToConsolidate()
                } else {
                    goToAbandon()
                }

            } else if (!gameViewModel.getSecondChance()) {
                gameViewModel.setSecondChange(true)
            } else {
                if (tense) {
                    mediaPlayer.stop()
                }
                gameViewModel.setGameStatus(0) // Game Lost
                goToSummary()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopTimer()
    }

    private fun checkAnswer(correctAnswer: String): Boolean {
        val rbSelected = binding.radioGroup.checkedRadioButtonId
        val answerSelected =
            binding.radioGroup.findViewById<RadioButton>(rbSelected).text.toString()

        if (rbSelected == -1) {
            Toast.makeText(requireContext(), R.string.msg_empty_answer, Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (answerSelected.equals(correctAnswer)) {
            onViewCreated(binding.root, null)
            return true
        }

        if (!gameViewModel.getSecondChance()) {
            Toast.makeText(
                requireContext(),
                R.string.msg_second_chance,
                Toast.LENGTH_SHORT
            ).show()
            playLosingMusic(true)

        } else {
            Toast.makeText(
                requireContext(),
                R.string.msg_incorrect_answer,
                Toast.LENGTH_SHORT
            ).show()
            playLosingMusic(false)

        }
        return false
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(30000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = (millisUntilFinished / 1000).toString()
                if ((millisUntilFinished / 1000).toInt() == 10 && !timerCancelledManually) {
                    playTenseMusic()
                    tense = true
                }
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                if (!timerCancelledManually) {
                    mediaPlayer.stop()
                    playLosingMusic(false)
                    goToSummary()
                }
            }
        }.start()
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
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
        val consolidateFragment = ConsolidateFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, consolidateFragment)
            .commit()
    }

    private fun goToSummary() {
        val summaryFragment = ResumeFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, summaryFragment)
            .commit()
    }

    private fun goToAbandon() {
        val abandonFragment = AbandonFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, abandonFragment)
            .commit()
    }

    private fun changeViewImage() {
        binding.ivODS.setImageResource(R.drawable.ods1)
    }
}