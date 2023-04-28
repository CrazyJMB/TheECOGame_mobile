package com.dds.theecogame.presentation.register.viewModel

import androidx.lifecycle.ViewModel
import com.dds.theecogame.presentation.game.viewModel.GameViewModel

class RegisterViewModel : ViewModel() {
    private lateinit var sharedViewModel: GameViewModel

    fun setSharedViewModel(gameViewModel: GameViewModel) {
        sharedViewModel = gameViewModel
    }
}