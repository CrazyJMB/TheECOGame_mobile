package com.dds.theecogame.presentation.game.view

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentConsolidateBinding
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.presentation.game.viewModel.GameViewModel

class ConsolidateFragment : Fragment() {

    private lateinit var binding: FragmentConsolidateBinding

    private val gameViewModel: GameViewModel by activityViewModels()

    private var countDownTimer: CountDownTimer? = null
    private var timerCancelledManually: Boolean = false
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConsolidateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTimer()
        startMusic()

        binding.ivAdvice.setOnClickListener {
            if (binding.tvConsolidateExplanation.visibility == View.INVISIBLE) {
                binding.tvConsolidateExplanation.visibility = View.VISIBLE
            } else {
                binding.tvConsolidateExplanation.visibility = View.INVISIBLE
            }
        }

        binding.btnConfirm.setOnClickListener {
            gameViewModel.setConsolidated(true)
            gameViewModel.setConsolidatedPoints()
            stopTimer()
            if (gameViewModel.getQuestionNumber() == 11) {
                goToSummary()
            } else {
                nextChallenge()
            }
        }

        binding.btnCancel.setOnClickListener {
            stopTimer()
            if (gameViewModel.getQuestionNumber() == 11) {
                goToSummary()
            } else {
                nextChallenge()
            }
        }

        binding.btnAbandon.setOnClickListener {
            gameViewModel.setGameStatus(1)
            gameViewModel.setConsolidated(true)
            gameViewModel.setConsolidatedPoints()
            stopTimer()
            goToSummary()
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(15000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                if (!timerCancelledManually) {
                    goToQuestions()
                }
            }
        }.start()
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        timerCancelledManually = true
        countDownTimer = null
    }

    private fun startMusic() {
        mediaPlayer = if (gameViewModel.getQuestionNumber() == 11) {
            MediaPlayer.create(requireContext(), R.raw.victoria)
        } else {
            MediaPlayer.create(requireContext(), R.raw.ganar_reto)
        }
        mediaPlayer.isLooping = false
        mediaPlayer.start()
    }

    private fun nextChallenge() {
        gameViewModel.gameLiveData.observe(requireActivity()) {
            when (it.getNextChallenge()) {
                is Game.Challenge.HangmanModel -> {
                    goToHangman()
                }

                is Game.Challenge.QuestionModel -> {
                    goToQuestions()
                }
            }
        }
    }

    private fun goToQuestions() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, QuestionFragment())
            .commit()
    }

    private fun goToHangman() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, HangmanFragment())
            .commit()
    }

    private fun goToSummary() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, ResumeFragment())
            .commit()
    }

}