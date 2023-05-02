package com.dds.theecogame.presentation.setting.view

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.databinding.ActivitySettingBinding
import com.dds.theecogame.data.local.DataStoreManager
import com.dds.theecogame.dataStore
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        val dataStoreManager = DataStoreManager(dataStore)

        // Default values
        lifecycleScope.launch(Dispatchers.IO) {
            dataStoreManager.getGeneralVolume().collect {
                binding.sbGeneralVolume.progress = it
            }

            dataStoreManager.getSoundVolume().collect {
                binding.sbSounds.progress = it
            }

            dataStoreManager.getMusicVolume().collect {
                binding.sbMusic.progress = it
            }
        }


        // Listener
        binding.sbGeneralVolume.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(general_volume: SeekBar) {
                lifecycleScope.launch(Dispatchers.IO) {
                    dataStoreManager.setGeneralVolume(general_volume.progress)
                }
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
                lifecycleScope.launch(Dispatchers.IO) {
                    dataStoreManager.setMusicVolume(music.progress)
                }
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
                lifecycleScope.launch(Dispatchers.IO) {
                    dataStoreManager.setSoundVolume(sounds.progress)
                }
            }

        })

        binding.btnSave.setOnClickListener {
            startActivity(Intent(this, MainScreenActivity::class.java))
        }
    }

}