package com.dds.theecogame.presentation.game.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
        initialize()

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            if (viewModel.getTimeEnded(sharedPref).toInt() == 0) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("¿Deseas abandonar?")
                builder.setMessage("Recuerda que si no has consolidado, obtendras 0 puntos")
                builder.setPositiveButton("Aceptar") { dialog, which ->
                    GameViewModel().changeGameStatus(sharedPref, "Abandoned")
                    val summaryFragment = ResumeFragment()
                    val fragmentManager = this.supportFragmentManager
                    fragmentManager.beginTransaction()
                        .replace(R.id.GameContainerView, summaryFragment)
                        .commit()
                }
                builder.setNegativeButton("Cancelar") { dialog, which ->
                //No hace nada
                }
                builder.show()
            } else {
                val mainScreen = Intent(this, MainScreenActivity::class.java)
                startActivity(mainScreen)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        //TODO("Que pasa cuando el usuario miminiza la aplicacion? Se deberia guardar el progreso actual")
    }

    override fun onDestroy() {
        super.onDestroy()
        TODO("Que pasa cuando se sale de una partida no acabada")
    }

    private fun initialize() {
        val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        GameViewModel().resetGameStats(sharedPref)
    }

}