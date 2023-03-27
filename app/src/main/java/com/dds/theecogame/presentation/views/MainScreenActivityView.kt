package com.dds.theecogame.presentation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dds.theecogame.R
import com.dds.theecogame.presentation.viewmodel.MainScreenViewModel

class MainScreenActivityView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        var viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]


    }
}