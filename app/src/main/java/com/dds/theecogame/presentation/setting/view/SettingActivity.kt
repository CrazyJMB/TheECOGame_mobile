package com.dds.theecogame.presentation.setting.view

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.activity.viewModels
import com.dds.theecogame.R
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
        viewModel.initialize(applicationContext)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        // Initial values SeekBar
        binding.sbGeneralVolume.progress = viewModel.getGeneralVolume()
        binding.sbMusic.progress = viewModel.getMusicVolume()
        binding.sbSounds.progress = viewModel.getSoundVolume()

        // Listener
        binding.sbGeneralVolume.setOnSeekBarChangeListener(object :
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
                viewModel.setGeneralVolume(general_volume.progress)
            }

        })

        binding.sbMusic.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(music: SeekBar, progress: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(music: SeekBar) {
                //do nothing
            }

            override fun onStopTrackingTouch(music: SeekBar) {
                viewModel.setMusicVolume(music.progress)
            }

        })

        binding.sbSounds.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sounds: SeekBar, progress: Int, p2: Boolean) {
                //do nothing
            }

            override fun onStartTrackingTouch(sounds: SeekBar) {
                //do nothing
            }

            override fun onStopTrackingTouch(sounds: SeekBar) {
                viewModel.setSoundVolume(sounds.progress)
            }

        })

        binding.btnSave.setOnClickListener {
            val i = Intent(this, MainScreenActivity::class.java)
            startActivity(i)
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.Topics)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spTopic.adapter = adapter
    }

}