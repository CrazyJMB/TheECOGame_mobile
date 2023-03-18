package com.dds.theecogame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.dds.theecogame.databinding.ActivityEstadisticaBinding
import com.dds.theecogame.databinding.ActivityMainScreenBinding
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

    fun initializeStats (){
        binding.numPartidasJugadas.setText(binding.numPartidasJugadas.text.toString() + " 0")
        binding.numPartidasGanadas.setText(binding.numPartidasGanadas.text.toString() + " 0")
        binding.numPartidasPerdidas.setText(binding.numPartidasPerdidas.text.toString() + " 0")
        binding.numPartidasAbandonadas.setText(binding.numPartidasAbandonadas.text.toString() + " 0")
        binding.tiempoPromedio.setText(binding.tiempoPromedio.text.toString() + " 0")
        binding.tiempoTotal.setText(binding.tiempoTotal.text.toString() + " 0")
        binding.numPreguntasAcertadas.setText(binding.numPreguntasAcertadas.text.toString() + " 0")
        binding.numPreguntasIncorrectas.setText(binding.numPreguntasIncorrectas.text.toString() + " 0")
        binding.nivelConocimientoODS.setText(binding.nivelConocimientoODS.text.toString() + " 0%")
    }
}