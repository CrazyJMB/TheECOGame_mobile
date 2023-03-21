package com.dds.theecogame.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dds.theecogame.R
import com.dds.theecogame.viewmodel.MainScreenViewModel

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        var viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]


    }
}