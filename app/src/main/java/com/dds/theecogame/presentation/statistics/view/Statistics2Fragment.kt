package com.dds.theecogame.presentation.statistics.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentStatistics2Binding
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.statistics.viewModel.StatisticsViewModel

class Statistics2Fragment: Fragment() {

    private lateinit var binding: FragmentStatistics2Binding
    private val viewModel: StatisticsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatistics2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeStats()

        binding.btnBack.setOnClickListener {
            goToMainScreen()
        }

        binding.ivLeft.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, Statistics1Fragment())
                .commit()
        }
    }

    private fun initializeStats (){
        val numCorrectQuestion = viewModel.getNumCorrectQuestions()
        binding.numPreguntasAcertadas.text = "${binding.numPreguntasAcertadas.text} $numCorrectQuestion"

        val numIncorrectQuestion = viewModel.getNumIncorrectQuestions()
        binding.numPreguntasIncorrectas.text = "${binding.numPreguntasIncorrectas.text} $numIncorrectQuestion"

        val numCorrectHangman = viewModel.getNumCorrectHangman()
        binding.numAhorcadosAcertadas.text = "${binding.numAhorcadosAcertadas.text} $numCorrectHangman"

        val numIncorrectHangman = viewModel.getNumIncorrectHangman()
        binding.numAhorcadosIncorrectas.text = "${binding.numAhorcadosIncorrectas.text} $numIncorrectHangman"

        val odsKnowledgeLevel = viewModel.getOdsKnowledgeLevel()
        binding.nivelConocimientoODS.text = "${binding.nivelConocimientoODS.text} $odsKnowledgeLevel%"
    }

    private fun goToMainScreen (){
        val mainScreen = Intent(activity, MainScreenActivity::class.java)
        startActivity(mainScreen)
    }
}