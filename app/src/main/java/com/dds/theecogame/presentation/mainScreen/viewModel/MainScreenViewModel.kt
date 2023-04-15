package com.dds.theecogame.presentation.mainScreen.viewModel

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.domain.builder.GameDirector
import com.dds.theecogame.domain.builder.concreteBuilder.QuestionsGameBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "GAME_PREFERENCES")

class MainScreenViewModel() : ViewModel() {

}


