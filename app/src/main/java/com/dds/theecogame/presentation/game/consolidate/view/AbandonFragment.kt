package com.dds.theecogame.presentation.game.consolidate.view

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentAbandonBinding
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.presentation.game.challenges.hangman.view.HangmanFragment
import com.dds.theecogame.presentation.game.challenges.question.view.QuestionFragment
import com.dds.theecogame.presentation.game.resume.ResumeFragment
import com.dds.theecogame.presentation.game.viewModel.GameViewModel

class AbandonFragment : Fragment() {

    private lateinit var binding: FragmentAbandonBinding
    private val gameViewModel: GameViewModel by activityViewModels()
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbandonBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startMusic()

        binding.btnConfirm.setOnClickListener {
            gameViewModel.setGameStatus(1)
            goToSummary()
        }

        binding.btnCancel.setOnClickListener {
            if (gameViewModel.getQuestionNumber() > 10) {
                goToSummary()
            } else {
                nextChallenge()
            }
        }
    }

    private fun nextChallenge() {
        gameViewModel.gameLiveData.observe(requireActivity()) {
            when (it.challengesList[gameViewModel.getQuestionNumber()]) {

                is Game.Challenge.HangmanModel -> goToHangman()
                is Game.Challenge.QuestionModel -> goToQuestions()
            }
        }
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