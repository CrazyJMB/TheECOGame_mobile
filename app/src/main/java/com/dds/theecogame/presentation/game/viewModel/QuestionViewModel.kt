package com.dds.theecogame.presentation.game.viewModel

import androidx.lifecycle.ViewModel
import com.dds.theecogame.domain.model.Question

class QuestionViewModel : ViewModel() {

    private lateinit var sharedViewModel: GameViewModel

    fun setSharedViewModel(gameViewModel: GameViewModel) {
        sharedViewModel = gameViewModel
    }
}