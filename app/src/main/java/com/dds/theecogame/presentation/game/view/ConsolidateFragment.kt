package com.dds.theecogame.presentation.game.view

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentConsolidateBinding
import com.dds.theecogame.presentation.game.viewModel.GameViewModel

class ConsolidateFragment : Fragment() {

    private lateinit var binding: FragmentConsolidateBinding

    private val gameViewModel: GameViewModel by activityViewModels()

    private var countDownTimer: CountDownTimer? = null
    private var timerCancelledManually: Boolean = false

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
            goToQuestions()
        }
        binding.btnCancel.setOnClickListener {
            stopTimer()
            goToQuestions()
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

    private fun goToQuestions() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, QuestionFragment())
            .commit()
    }

}