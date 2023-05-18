package com.dds.theecogame.presentation.ranking.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivityMainScreenBinding
import com.dds.theecogame.databinding.ActivityRankingBinding
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.mainScreen.viewModel.MainScreenViewModel
import com.dds.theecogame.presentation.ranking.viewModel.RankingViewModel

class RankingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRankingBinding
    private val viewModel: RankingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainScreenActivity::class.java))
        }
    }
}