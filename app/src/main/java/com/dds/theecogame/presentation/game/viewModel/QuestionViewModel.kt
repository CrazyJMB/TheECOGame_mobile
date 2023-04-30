package com.dds.theecogame.presentation.game.viewModel

import androidx.lifecycle.ViewModel

class QuestionViewModel : ViewModel() {

    private lateinit var sharedViewModel: GameViewModel

    fun setSharedViewModel(gameViewModel: GameViewModel) {
        sharedViewModel = gameViewModel
    }
}