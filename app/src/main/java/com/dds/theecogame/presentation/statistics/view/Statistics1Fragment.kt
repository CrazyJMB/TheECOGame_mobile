package com.dds.theecogame.presentation.statistics.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentKeyboardBinding
import com.dds.theecogame.databinding.FragmentStatistics1Binding
import com.dds.theecogame.presentation.game.view.QuestionFragment
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.statistics.viewModel.StatisticsViewModel

class Statistics1Fragment : Fragment() {
    private lateinit var binding: FragmentStatistics1Binding
    private val viewModel: StatisticsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatistics1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeStats()

        binding.btnBack.setOnClickListener {
            goToMainScreen()
        }

        binding.ivRight.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, Statistics2Fragment())
                .commit()
        }
    }

    private fun initializeStats (){
        //Num partidas jugadas, partidas ganadas, partidas perdidas, partidas abandonadas, tiempo promedio, tiempo total


        val numGamesPlayed = viewModel.getNumGamesPlayed()
        binding.numPartidasJugadas.text = "${binding.numPartidasJugadas.text} $numGamesPlayed"

        val numGamesWon = StatisticsViewModel().getNumGamesWon()
        binding.numPartidasGanadas.text = "${binding.numPartidasGanadas.text} $numGamesWon"

        val numGamesLost = StatisticsViewModel().getNumGamesLost()
        binding.numPartidasPerdidas.text = "${binding.numPartidasPerdidas.text} $numGamesLost"

        val numGamesAbandoned = StatisticsViewModel().getNumGamesAbandoned()
        binding.numPartidasAbandonadas.text = "${binding.numPartidasAbandonadas.text} $numGamesAbandoned"

        val avgTimePerGame = StatisticsViewModel().getAvgTimePerGame()
        binding.tiempoPromedio.text = "${binding.tiempoPromedio.text} $avgTimePerGame"

        val totalTimePlayed = StatisticsViewModel().getTotalTimePlayed()
        binding.tiempoTotal.text = "${binding.tiempoTotal.text} $totalTimePlayed"

    }

    private fun goToMainScreen (){
        val mainScreen = Intent(activity, MainScreenActivity::class.java)
        startActivity(mainScreen)
    }

}