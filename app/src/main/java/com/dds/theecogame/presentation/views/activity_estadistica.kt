package com.dds.theecogame.presentation.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dds.theecogame.databinding.ActivityEstadisticaBinding
import com.dds.theecogame.presentation.viewmodel.EstadisticaViewModel

class activity_estadistica : AppCompatActivity() {
    private lateinit var binding: ActivityEstadisticaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstadisticaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeStats()
    }

    fun goToMainScreen (view: View){
        val mainScreen = Intent(this, MainScreenActivityView::class.java)
        startActivity(mainScreen)
    }

    // Initializes the statistics screen with default values.
    private fun initializeStats() {
        val numGamesPlayed = EstadisticaViewModel().getNumGamesPlayed()
        binding.numPartidasJugadas.text = "${binding.numPartidasJugadas.text} $numGamesPlayed"

        val numGamesWon = EstadisticaViewModel().getNumGamesWon()
        binding.numPartidasGanadas.text = "${binding.numPartidasGanadas.text} $numGamesWon"

        val numGamesLost = EstadisticaViewModel().getNumGamesLost()
        binding.numPartidasPerdidas.text = "${binding.numPartidasPerdidas.text} $numGamesLost"

        val numGamesAbandoned = EstadisticaViewModel().getNumGamesAbandoned()
        binding.numPartidasAbandonadas.text = "${binding.numPartidasAbandonadas.text} $numGamesAbandoned"

        val avgTimePerGame = EstadisticaViewModel().getAvgTimePerGame()
        binding.tiempoPromedio.text = "${binding.tiempoPromedio.text} $avgTimePerGame"

        val totalTimePlayed = EstadisticaViewModel().getTotalTimePlayed()
        binding.tiempoTotal.text = "${binding.tiempoTotal.text} $totalTimePlayed"

        val numCorrectAnswers = EstadisticaViewModel().getNumCorrectAnswers()
        binding.numPreguntasAcertadas.text = "${binding.numPreguntasAcertadas.text} $numCorrectAnswers"

        val numIncorrectAnswers = EstadisticaViewModel().getNumIncorrectAnswers()
        binding.numPreguntasIncorrectas.text = "${binding.numPreguntasIncorrectas.text} $numIncorrectAnswers"

        val odsKnowledgeLevel = EstadisticaViewModel().getOdsKnowledgeLevel()
        binding.nivelConocimientoODS.text = "${binding.nivelConocimientoODS.text} $odsKnowledgeLevel%"
    }

}