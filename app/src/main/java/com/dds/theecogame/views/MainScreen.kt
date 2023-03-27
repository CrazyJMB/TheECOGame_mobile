package com.dds.theecogame.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dds.theecogame.activity_estadistica
import com.dds.theecogame.databinding.ActivityMainScreenBinding
import com.dds.theecogame.viewmodel.MainScreenViewModel

class MainScreen : AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding

    lateinit var viewModel: MainScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]

        binding.btnStatistics.setOnClickListener{
            val intent = Intent(this, activity_estadistica::class.java)
            startActivity(intent)
        }
    }
}