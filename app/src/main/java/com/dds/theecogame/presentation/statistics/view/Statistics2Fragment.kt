package com.dds.theecogame.presentation.statistics.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.R
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.StatisticsRepositoryImpl

import com.dds.theecogame.databinding.FragmentStatistics2Binding
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.repository.StatisticsRepository
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.statistics.viewModel.StatisticsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Statistics2Fragment: Fragment() {

    private lateinit var binding: FragmentStatistics2Binding
    private val viewModel: StatisticsViewModel by viewModels()
    private val statisticsRepository: StatisticsRepository = StatisticsRepositoryImpl()

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

        lifecycleScope.launch {
           statisticsRepository.getStatistics(Application.getUser()!!.id).collect{
               when (it) {
                   is Resource.Loading -> {}
                   is Resource.Success -> {
                       val stats = it.data

                       val numCorrectQuestion = stats!!.question_correct_count.toString()
                       binding.numPreguntasAcertadas.text =
                           "${binding.numPreguntasAcertadas.text} $numCorrectQuestion"

                       val numIncorrectQuestion = stats!!.question_failed_count.toString()
                       binding.numPreguntasIncorrectas.text =
                           "${binding.numPreguntasIncorrectas.text} $numIncorrectQuestion"

                       val numCorrectHangman = stats!!.hangman_correct_count.toString()
                       binding.numAhorcadosAcertadas.text =
                           "${binding.numAhorcadosAcertadas.text} $numCorrectHangman"

                       val numIncorrectHangman = stats!!.hangman_failed_count.toString()
                       binding.numAhorcadosIncorrectas.text =
                           "${binding.numAhorcadosIncorrectas.text} $numIncorrectHangman"

                       val odsKnowledgeLevel = stats!!.ods_knowledge.toString()
                       binding.nivelConocimientoODS.text =
                           "${binding.nivelConocimientoODS.text} $odsKnowledgeLevel%"
                   }
                   is Resource.Error -> {}
               }
           }

        }

    }

    private fun goToMainScreen (){
        val mainScreen = Intent(activity, MainScreenActivity::class.java)
        startActivity(mainScreen)
    }
}