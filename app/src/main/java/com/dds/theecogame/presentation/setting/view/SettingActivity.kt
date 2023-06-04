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
import com.dds.theecogame.domain.model.Settings
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.setting.viewModel.SettingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private lateinit var audioManager: AudioManager

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var viewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        settingsRepository = SettingsRepository(dataStore)
        viewModel = SettingViewModel(settingsRepository)

        viewModel.settingsLiveData.observe(this) { settings ->
            updateUI(settings)
        }


        // Listener
        binding.sbGeneralVolume.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(general_volume: SeekBar) {
                viewModel.updateGeneralVolume(general_volume.progress)
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
                viewModel.updateMusicVolume(music.progress)
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
                viewModel.updateSoundVolume(sounds.progress)
            }

        })

        binding.btnRestore.setOnClickListener {
            viewModel.restorePreviousSettings()
        }

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.saveSettings()

                withContext(Dispatchers.Main) {
                    startActivity(Intent(this@SettingActivity, MainScreenActivity::class.java))
                }
            }
        }

        binding.ibBack3.setOnClickListener {
            startActivity(Intent(this, MainScreenActivity::class.java))
        }

    }

    private fun updateUI(settings: Settings) {
        binding.sbGeneralVolume.progress = settings.volume
        binding.sbMusic.progress = settings.music
        binding.sbSounds.progress = settings.sound
    }
}