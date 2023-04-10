package com.dds.theecogame.presentation.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dds.theecogame.databinding.ActivityMainScreenBinding
import com.dds.theecogame.presentation.viewmodel.MainScreenViewModel

class MainScreenActivityView : AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding

    lateinit var viewModel: MainScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]

        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, GameActivityView::class.java)
            startActivity(intent)
        }

        binding.btnStatistics.setOnClickListener {
            val intent = Intent(this, activity_estadistica::class.java)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, SettingActivityView::class.java)
            startActivity(intent)
        }
    }
}