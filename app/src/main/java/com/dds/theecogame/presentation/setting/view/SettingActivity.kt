package com.dds.theecogame.presentation.setting.view

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.databinding.ActivitySettingBinding
import com.dds.theecogame.data.local.SettingsRepository
import com.dds.theecogame.dataStore
import com.dds.theecogame.domain.memento.Settings
import com.dds.theecogame.domain.memento.SettingsCareTaker
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private lateinit var audioManager: AudioManager

    private lateinit var settingsRepository: SettingsRepository

    private lateinit var settings: Settings
    private var careTaker: SettingsCareTaker = SettingsCareTaker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        settingsRepository = SettingsRepository(dataStore)


        // Default values
        lifecycleScope.launch(Dispatchers.IO) {
            settings = loadSettings() ?: Settings(100, 100, 100, 100)
            careTaker.saveState(settings)

            // Update UI
            withContext(Dispatchers.Main) {
                binding.sbGeneralVolume.progress = settings.volume
                binding.sbMusic.progress = settings.music
                binding.sbSounds.progress = settings.sound
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
                settings.volume = general_volume.progress
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
                settings.music = music.progress
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
                settings.sound = sounds.progress
            }

        })

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                settingsRepository.saveSettings(settings)

                withContext(Dispatchers.Main) {
                    startActivity(Intent(this@SettingActivity, MainScreenActivity::class.java))
                }
            }
        }

    }

    private suspend fun loadSettings(): Settings? {
        return settingsRepository.settingsFlow.firstOrNull()
    }

}