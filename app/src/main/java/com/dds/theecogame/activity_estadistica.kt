package com.dds.theecogame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dds.theecogame.databinding.ActivityEstadisticaBinding
import com.dds.theecogame.ui.MainScreen

class activity_estadistica : AppCompatActivity() {
    private lateinit var binding: ActivityEstadisticaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_estadistica)
        binding = ActivityEstadisticaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeStats()
    }

    fun goToMainScreen (view: View){
        val mainScreen = Intent(this, MainScreen::class.java)
        startActivity(mainScreen)
    }

    private fun initializeStats (){
        binding.numPartidasJugadas.text = binding.numPartidasJugadas.text.toString() + " 0"
        binding.numPartidasGanadas.text = binding.numPartidasGanadas.text.toString() + " 0"
        binding.numPartidasPerdidas.text = binding.numPartidasPerdidas.text.toString() + " 0"
        binding.numPartidasAbandonadas.text = binding.numPartidasAbandonadas.text.toString() + " 0"
        binding.tiempoPromedio.text = binding.tiempoPromedio.text.toString() + " 0"
        binding.tiempoTotal.text = binding.tiempoTotal.text.toString() + " 0"
        binding.numPreguntasAcertadas.text = binding.numPreguntasAcertadas.text.toString() + " 0"
        binding.numPreguntasIncorrectas.text = binding.numPreguntasIncorrectas.text.toString() + " 0"
        binding.nivelConocimientoODS.text = binding.nivelConocimientoODS.text.toString() + " 0%"
    }
}