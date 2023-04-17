package com.dds.theecogame.presentation.setting.view

import android.app.Application
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.activity.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.data.local.DataStoreManager
import com.dds.theecogame.databinding.ActivitySettingBinding
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.setting.viewModel.SettingViewModel

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private lateinit var audioManager: AudioManager

    val viewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.initialize(application)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        binding.seekBarGeneralVolume.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                general_volume: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                //do nothing
                viewModel.setGeneralVolume(general_volume.progress)

                if (fromUser) {
                    val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
                    val volume = maxVolume * progress / 100
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)
                }
            }

            override fun onStartTrackingTouch(general_volume: SeekBar) {
            }

            override fun onStopTrackingTouch(general_volume: SeekBar) {
            }

        })

        binding.seekBarMusic.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(music: SeekBar, progress: Int, p2: Boolean) {
                viewModel.setMusicVolume(music.progress)
            }

            override fun onStartTrackingTouch(music: SeekBar) {
            }

            override fun onStopTrackingTouch(music: SeekBar) {
            }

        })

        binding.seekBarSounds.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sounds: SeekBar, progress: Int, p2: Boolean) {
                viewModel.setSoundVolume(sounds.progress)
            }

            override fun onStartTrackingTouch(sounds: SeekBar) {
            }

            override fun onStopTrackingTouch(sounds: SeekBar) {
            }

        })

        binding.buttonSave.setOnClickListener {
            val i = Intent(this@SettingActivity, MainScreenActivity::class.java)
            startActivity(i)
        }
    }
}