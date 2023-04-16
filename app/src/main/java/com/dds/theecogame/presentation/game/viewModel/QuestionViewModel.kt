package com.dds.theecogame.presentation.game.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionViewModel : ViewModel() {

    suspend fun saveValue(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}