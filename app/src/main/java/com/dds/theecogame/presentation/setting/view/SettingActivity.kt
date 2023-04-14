package com.dds.theecogame.presentation.setting.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.Spinner
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivitySettingBinding
import com.dds.theecogame.domain.model.Settings
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_setting)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val mainScreen = Intent(this, MainScreenActivity::class.java)
            startActivity(mainScreen)
        }


        val settings = Settings(0, 0, 0)

        val general_volume = findViewById<SeekBar>(R.id.seekBar_general_volume)
        general_volume?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(general_volume: SeekBar, progress: Int, p2: Boolean) {
                //do nothing
            }

            override fun onStartTrackingTouch(general_volume: SeekBar) {
                //do nothing
            }

            override fun onStopTrackingTouch(general_volume: SeekBar) {
                settings.general_volume = general_volume.progress
            }

        })

        val music = findViewById<SeekBar>(R.id.seekBar_music)
        music?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(music: SeekBar, progress: Int, p2: Boolean) {
                //do nothing
            }

            override fun onStartTrackingTouch(music: SeekBar) {
                //do nothing
            }

            override fun onStopTrackingTouch(music: SeekBar) {
                settings.music = music.progress
            }

        })

        val sounds = findViewById<SeekBar>(R.id.seekBar_sounds)
        sounds?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sounds: SeekBar, progress: Int, p2: Boolean) {
                //do nothing
            }

            override fun onStartTrackingTouch(sounds: SeekBar) {
                //do nothing
            }

            override fun onStopTrackingTouch(sounds: SeekBar) {
                settings.sounds = sounds.progress
            }

        })

        //For the spinner
        val topics = resources.getStringArray(R.array.Topics)

        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, topics)
            spinner.adapter = adapter
        }

    }
}