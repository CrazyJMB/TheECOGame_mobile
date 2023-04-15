package com.dds.theecogame.presentation.game.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dds.theecogame.databinding.ActivityGameBinding
import com.dds.theecogame.domain.model.Challenge
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create game
        viewModel.createGame()

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainScreenActivity::class.java)
            startActivity(intent)
            TODO("NO se puede salir si no se ha consolidado -> Mensaje")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TODO("Que pasa cuando se sale de una partida no acabada")
    }


}