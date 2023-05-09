package com.dds.theecogame.presentation.statistics.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.R
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.StatisticsRepositoryImpl
import com.dds.theecogame.databinding.FragmentKeyboardBinding
import com.dds.theecogame.databinding.FragmentStatistics1Binding
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.repository.StatisticsRepository
import com.dds.theecogame.presentation.game.view.QuestionFragment
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.statistics.viewModel.StatisticsViewModel
import kotlinx.coroutines.launch

class Statistics1Fragment : Fragment() {
    private lateinit var binding: FragmentStatistics1Binding
    private val viewModel: StatisticsViewModel by viewModels()
    private val statisticsRepository: StatisticsRepository = StatisticsRepositoryImpl()

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
        lifecycleScope.launch {
            statisticsRepository.getStatistics(Application.getUser()!!.id).collect{
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val stats = it.data

                        val numGamesWon = stats!!.win_count
                        binding.numPartidasGanadas.text =
                            "${binding.numPartidasGanadas.text} $numGamesWon"

                        val numGamesLost = stats!!.lose_count
                        binding.numPartidasPerdidas.text =
                            "${binding.numPartidasPerdidas.text} $numGamesLost"

                        val numGamesAbandoned = stats!!.quit_count
                        binding.numPartidasAbandonadas.text =
                            "${binding.numPartidasAbandonadas.text} $numGamesAbandoned"

                        val numGamesPlayed = numGamesLost + numGamesWon + numGamesAbandoned
                        binding.numPartidasJugadas.text =
                            "${binding.numPartidasJugadas.text} $numGamesPlayed"

                        val totalTimePlayed = stats!!.time_ingame
                        binding.tiempoTotal.text = "${binding.tiempoTotal.text} $totalTimePlayed"

                        if (numGamesPlayed != 0 && totalTimePlayed != 0) {
                            val avgTimePerGame = (totalTimePlayed/numGamesPlayed).toInt()
                            binding.tiempoPromedio.text =
                            "${binding.tiempoPromedio.text} $avgTimePerGame"
                        } else {
                            binding.tiempoPromedio.text = "${binding.tiempoPromedio.text} 0"
                        }
                    }
                    is Resource.Error ->{}
                }
            }
        }
    }

    private fun goToMainScreen (){
        val mainScreen = Intent(activity, MainScreenActivity::class.java)
        startActivity(mainScreen)
    }

}