package com.dds.theecogame.presentation.statistics.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dds.theecogame.databinding.ActivityEstadisticaBinding
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.statistics.viewModel.StatisticsViewModel

class StatisticsFragment : AppCompatActivity() {
    private lateinit var binding: ActivityEstadisticaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstadisticaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeStats()
    }

    fun goToMainScreen(view: View) {
        val mainScreen = Intent(this, MainScreenActivity::class.java)
        startActivity(mainScreen)
    }

    // Initializes the statistics screen with default values.
    private fun initializeStats() {
        val numGamesPlayed = StatisticsViewModel().getNumGamesPlayed()
        binding.numPartidasJugadas.text = "${binding.numPartidasJugadas.text} $numGamesPlayed"

        val numGamesWon = StatisticsViewModel().getNumGamesWon()
        binding.numPartidasGanadas.text = "${binding.numPartidasGanadas.text} $numGamesWon"

        val numGamesLost = StatisticsViewModel().getNumGamesLost()
        binding.numPartidasPerdidas.text = "${binding.numPartidasPerdidas.text} $numGamesLost"

        val numGamesAbandoned = StatisticsViewModel().getNumGamesAbandoned()
        binding.numPartidasAbandonadas.text =
            "${binding.numPartidasAbandonadas.text} $numGamesAbandoned"

        val avgTimePerGame = StatisticsViewModel().getAvgTimePerGame()
        binding.tiempoPromedio.text = "${binding.tiempoPromedio.text} $avgTimePerGame"

        val totalTimePlayed = StatisticsViewModel().getTotalTimePlayed()
        binding.tiempoTotal.text = "${binding.tiempoTotal.text} $totalTimePlayed"

        val numCorrectAnswers = StatisticsViewModel().getNumCorrectAnswers()
        binding.numPreguntasAcertadas.text =
            "${binding.numPreguntasAcertadas.text} $numCorrectAnswers"

        val numIncorrectAnswers = StatisticsViewModel().getNumIncorrectAnswers()
        binding.numPreguntasIncorrectas.text =
            "${binding.numPreguntasIncorrectas.text} $numIncorrectAnswers"

        val odsKnowledgeLevel = StatisticsViewModel().getOdsKnowledgeLevel()
        binding.nivelConocimientoODS.text =
            "${binding.nivelConocimientoODS.text} $odsKnowledgeLevel%"
    }

}