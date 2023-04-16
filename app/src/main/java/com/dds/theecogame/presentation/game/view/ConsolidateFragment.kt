package com.dds.theecogame.presentation.game.view

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentConsolidateBinding
import com.dds.theecogame.presentation.game.viewModel.GameViewModel

class ConsolidateFragment : Fragment() {
    private lateinit var binding: FragmentConsolidateBinding
    private var countDownTimer: CountDownTimer? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConsolidateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTimer()

        binding.Advertencia.setOnClickListener{
            if (binding.ExplicacionConsolidar.visibility == View.INVISIBLE){
                binding.ExplicacionConsolidar.visibility = View.VISIBLE
            } else {
                binding.ExplicacionConsolidar.visibility = View.INVISIBLE
            }
        }

        binding.Si.setOnClickListener{
            val sharedPref = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            GameViewModel().addConsolidatePoints(sharedPref)
            stopTimer()
            goToQuestions()
        }
        binding.No.setOnClickListener{
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
                goToQuestions()
            }
        }.start()
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    private fun goToQuestions() {
        val questionFragment = QuestionFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, questionFragment)
            .commit()
    }

}