package com.dds.theecogame.presentation.game.viewModel

import androidx.datastore.preferences.core.*
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
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