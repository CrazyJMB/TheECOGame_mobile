package com.dds.theecogame.presentation.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dds.theecogame.databinding.ActivityGameBinding

class GameActivityView : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Volver.setOnClickListener {
            val intent = Intent(this, MainScreenActivityView::class.java)
            startActivity(intent)
            TODO("NO se puede salir si no se ha consolidado -> Mensaje")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TODO("Que pasa cuando se sale de una partida no acabada")
    }
}