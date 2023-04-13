package com.dds.theecogame.presentation.game.view

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentConsolidateBinding
import com.dds.theecogame.databinding.FragmentResumenBinding
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity

class ConsolidateFragment : Fragment() {
    private lateinit var binding: FragmentConsolidateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConsolidateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Si.setOnClickListener{
            GameViewModel().saveAcumulatedPoints(0)
            goToQuestions()
        }
        binding.No.setOnClickListener{
            goToQuestions()
        }
    }

    private fun startTimer() {
        object : CountDownTimer(15000, 1000) {

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

    private fun goToQuestions() {
        val questionFragment = QuestionFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, questionFragment)
            .commit()
    }

}