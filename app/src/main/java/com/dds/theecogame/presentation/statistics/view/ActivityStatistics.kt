package com.dds.theecogame.presentation.statistics.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dds.theecogame.databinding.ActivityEstadisticaBinding
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.statistics.viewModel.StatisticsViewModel

class ActivityStatistics : AppCompatActivity() {
    private lateinit var binding: ActivityEstadisticaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstadisticaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}