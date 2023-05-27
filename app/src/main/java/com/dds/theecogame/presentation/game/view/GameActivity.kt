package com.dds.theecogame.presentation.game.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle.Delegate
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivityGameBinding
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.userManagement.view.UserManagementActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.properties.Delegates

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val viewModel: GameViewModel by viewModels()
    private lateinit var mediaPlayer: MediaPlayer
    private var isMusicPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.startNumberHelp()

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            @SuppressLint("SuspiciousIndentation")
            override fun handleOnBackPressed() {
                if (!viewModel.getGameEnded()) {
                    val builder = AlertDialog.Builder(
                        ContextThemeWrapper(
                            this@GameActivity,
                            R.style.alert_style
                        )
                    )
                    builder.setTitle(R.string.question_quit)
                    builder.setPositiveButton(R.string.alert_confirm) { _, _ ->
                        val fragment = ResumeFragment()
                        val fragmentManager = supportFragmentManager
                        fragmentManager.beginTransaction()
                            .replace(R.id.GameContainerView, fragment)
                            .commit()
                    }
                    builder.setNegativeButton(R.string.alert_cancel) { _, _ ->
                        //No hace nada
                    }
                    val alertDialog = builder.create()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    alertDialog.show()
                } else {
                    val mainScreen = Intent(this@GameActivity, MainScreenActivity::class.java)
                    startActivity(mainScreen)
                }
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)

        viewModel.startMusic(this)
        isMusicPlaying = true
        viewModel.createGame(this)
        viewModel.gameLiveData.observe(this) {
            when (it.getNextChallenge()) {
                is Game.Challenge.HangmanModel -> {
                    val fragment = HangmanFragment()
                    val fragmentManager = this.supportFragmentManager
                    fragmentManager.beginTransaction()
                        .replace(R.id.GameContainerView, fragment)
                        .commit()
                }

                is Game.Challenge.QuestionModel -> {
                    val fragment = QuestionFragment()
                    val fragmentManager = this.supportFragmentManager
                    fragmentManager.beginTransaction()
                        .replace(R.id.GameContainerView, fragment)
                        .commit()
                }
            }
        }
        viewModel.setTimeStart()

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMute.setOnClickListener {
            if (isMusicPlaying) {
                viewModel.pauseMusic()
                binding.btnMute.setImageResource(R.drawable.mute)
                isMusicPlaying = false
            } else {
                viewModel.resumeMusic()
                binding.btnMute.setImageResource(R.drawable.sound)
                isMusicPlaying = true
            }
        }

        val normalTint = binding.btnClues.imageTintList
        viewModel.numberUsedHelp.observe(this) {
            if (viewModel.numberUsedHelp.value == 2) {
                binding.btnClues.isEnabled = false
                binding.btnClues.imageTintList = ColorStateList.valueOf(Color.GRAY)
            }
        }

        binding.btnClues.setOnClickListener {
            val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.alert_style))
            builder.setTitle(R.string.clues)
            builder.setMessage(R.string.clues_description)
            builder.setPositiveButton(R.string.alert_confirm) { _, _ ->
                viewModel.setUsedHelp(true)
                viewModel.addNumberHelp()
                viewModel.stopCountDownTimer()

                val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.alert_style))
                builder.setMessage("PISTA: " + viewModel.currentChallengeClue)
                builder.setPositiveButton("OK") { _, _ ->
                    viewModel.resumeCountDownTimer()
                    binding.btnClues.isEnabled = false
                    binding.btnClues.imageTintList = ColorStateList.valueOf(Color.GRAY)
                }
                val alertDialog = builder.create()
                alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.show()

            }
            builder.setNegativeButton(R.string.alert_cancel) { _, _ ->
                //No hace nada
            }
            val alertDialog = builder.create()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
        }

        viewModel.inFragmentChallenges.observe(this) {
            if (it && (viewModel.numberUsedHelp.value!! < 2)) {
                binding.btnClues.isEnabled = true
                binding.btnClues.imageTintList = normalTint
            } else {
                binding.btnClues.isEnabled = false
                binding.btnClues.imageTintList = ColorStateList.valueOf(Color.GRAY)
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