package com.dds.theecogame.presentation.game.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivityGameBinding
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
        viewModel.createGame(applicationContext)

        binding.btnBack.setOnClickListener {
            when (viewModel.getConsolidated()) {
                true -> startActivity(Intent(this, MainScreenActivity::class.java))

                false -> Toast.makeText(
                    this,
                    R.string.msg_consolidated,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        //TODO("Que pasa cuando el usuario miminiza la aplicacion? Se deberia guardar el progreso actual")
    }

    override fun onDestroy() {
        super.onDestroy()
        TODO("Que pasa cuando se sale de una partida sin acabar?")
    }


}