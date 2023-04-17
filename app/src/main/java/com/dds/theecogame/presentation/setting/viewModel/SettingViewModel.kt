package com.dds.theecogame.presentation.setting.viewModel

import android.app.Application
import android.content.Context
import android.provider.ContactsContract.Data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.data.local.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingViewModel() : ViewModel() {

    private lateinit var dataStore: DataStoreManager

    fun initialize(context: Context) {
        dataStore = DataStoreManager(context)
    }

    fun setGeneralVolume(generalVolume: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.setGeneralVolume(generalVolume)
        }
    }

    fun setMusicVolume(musicVolume: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.setMusicVolume(musicVolume)
        }
    }

    fun setSoundVolume(soundVolume: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.setSoundVolume(soundVolume)
        }
    }

    fun getGeneralVolume(): Int {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getGeneralVolume().collect() {
                return@collect
            }
        }
        return 100
    }

    fun getMusicVolume(): Int {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getMusicVolume().collect() {
                return@collect
            }
        }
        return 100
    }

    fun getSoundVolume(): Int {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getSoundVolume().collect() {
                return@collect
            }
        }
        return 100
    }
}


