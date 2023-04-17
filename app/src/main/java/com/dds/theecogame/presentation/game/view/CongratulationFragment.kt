package com.dds.theecogame.presentation.game.view

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dds.theecogame.R
import com.dds.theecogame.R.raw
import com.dds.theecogame.databinding.FragmentCongratulationsBinding
import com.dds.theecogame.presentation.game.viewModel.GameViewModel

class CongratulationFragment : Fragment() {

    private lateinit var binding: FragmentCongratulationsBinding
    private lateinit var mediaPlayer: MediaPlayer

    private val gameViewModel: GameViewModel by activityViewModels()
    private var countDownTimer: CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startMusic()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCongratulationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        binding.title.setOnClickListener {
            mediaPlayer.stop()
            if (gameViewModel.getQuestionNumber() == 11) {
                goToSummary()
            } else {
                if (!gameViewModel.getConsolidated()) {
                    goToConsolidate()
                } else {
                    goToQuestions()
                }
            }
        }

    }

    private fun startMusic() {
        mediaPlayer = if (gameViewModel.getQuestionNumber() == 11) {
            MediaPlayer.create(requireContext(), raw.victoria)
        } else {
            MediaPlayer.create(requireContext(), raw.ganar_reto)
        }
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

    private fun goToQuestions() {
        val questionFragment = QuestionFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, questionFragment)
            .commit()
    }
}