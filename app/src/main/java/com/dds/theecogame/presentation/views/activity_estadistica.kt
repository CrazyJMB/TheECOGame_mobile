package com.dds.theecogame.presentation.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dds.theecogame.databinding.ActivityEstadisticaBinding

class activity_estadistica : AppCompatActivity() {
    private lateinit var binding: ActivityEstadisticaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_estadistica)
        binding = ActivityEstadisticaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeStats()
    }

    fun goToMainScreen(view: View) {
        val mainScreen = Intent(this, MainScreenActivityView::class.java)
        startActivity(mainScreen)
    }

    // Initializes the statistics screen with default values.
    private fun initializeStats() {
        val numGamesPlayed = 0
        binding.numPartidasJugadas.text = "${binding.numPartidasJugadas.text} $numGamesPlayed"

        val numGamesWon = 0
        binding.numPartidasGanadas.text = "${binding.numPartidasGanadas.text} $numGamesWon"

        val numGamesLost = 0
        binding.numPartidasPerdidas.text = "${binding.numPartidasPerdidas.text} $numGamesLost"

        val numGamesAbandoned = 0
        binding.numPartidasAbandonadas.text =
            "${binding.numPartidasAbandonadas.text} $numGamesAbandoned"

        val avgTimePerGame = 0
        binding.tiempoPromedio.text = "${binding.tiempoPromedio.text} $avgTimePerGame"

        val totalTimePlayed = 0
        binding.tiempoTotal.text = "${binding.tiempoTotal.text} $totalTimePlayed"

        val numCorrectAnswers = 0
        binding.numPreguntasAcertadas.text =
            "${binding.numPreguntasAcertadas.text} $numCorrectAnswers"

        val numIncorrectAnswers = 0
        binding.numPreguntasIncorrectas.text =
            "${binding.numPreguntasIncorrectas.text} $numIncorrectAnswers"

        val odsKnowledgeLevel = "0%"
        binding.nivelConocimientoODS.text =
            "${binding.nivelConocimientoODS.text} $odsKnowledgeLevel"
    }

}