package com.dds.theecogame.presentation.setting.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import com.dds.theecogame.databinding.ActivitySettingBinding
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.setting.viewModel.SettingViewModel

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    val viewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.initialize(applicationContext)
    }

    override fun onStart() {
        super.onStart()

        // Initials values SeekBar
        binding.seekBarGeneralVolume.progress = viewModel.getGeneralVolume()
        binding.seekBarMusic.progress = viewModel.getMusicVolume()
        binding.seekBarSounds.progress = viewModel.getSoundVolume()

        // Listener
        binding.seekBarGeneralVolume.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(general_volume: SeekBar, progress: Int, p2: Boolean) {
                //do nothing
            }

            override fun onStartTrackingTouch(general_volume: SeekBar) {
                //do nothing
            }

            override fun onStopTrackingTouch(general_volume: SeekBar) {
                viewModel.setGeneralVolume(general_volume.progress)
            }

        })

        binding.seekBarMusic.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(music: SeekBar, progress: Int, p2: Boolean) {
                //do nothing
            }

            override fun onStartTrackingTouch(music: SeekBar) {
                //do nothing
            }

            override fun onStopTrackingTouch(music: SeekBar) {
                viewModel.setMusicVolume(music.progress)
            }

        })

        binding.seekBarSounds.setOnSeekBarChangeListener(object :
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

        binding.buttonSave.setOnClickListener {
            val i = Intent(this, MainScreenActivity::class.java)
            startActivity(i)
        }
    }

}