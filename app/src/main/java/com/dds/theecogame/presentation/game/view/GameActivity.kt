package com.dds.theecogame.presentation.game.view

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
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
    private lateinit var mediaPlayer: MediaPlayer
    private var isMusicPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.startMusic(this)
        isMusicPlaying = true
        viewModel.createGame(this)
        viewModel.setTimeStart()

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMute.setOnClickListener {
            if (isMusicPlaying) {
                viewModel.pauseMusic()
                binding.btnMute.setImageResource(R.drawable.sound)
                isMusicPlaying = false
            } else {
                viewModel.resumeMusic()
                binding.btnMute.setImageResource(R.drawable.mute)
                isMusicPlaying = true
            }
        }

        binding.btnBack.setOnClickListener {
            if (!viewModel.getGameEnded()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.alert_title_quit)
                builder.setMessage(R.string.alert_msg_quit)
                builder.setPositiveButton(R.string.alert_confirm) { _, _ ->
                    viewModel.setGameStatus(1) // Game abandoned
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.GameContainerView, ResumeFragment())
                        .commit()
                }
                builder.setNegativeButton(R.string.alert_cancel) { _, _ ->
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
        viewModel.releaseMusic()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.releaseMusic()
    }

}