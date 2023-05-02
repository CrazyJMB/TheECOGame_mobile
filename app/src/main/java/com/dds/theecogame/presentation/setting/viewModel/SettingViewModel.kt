package com.dds.theecogame.presentation.setting.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SettingViewModel(application: Application) : ViewModel() {

//    private val dataStore = (application as MyApp).dataStoreManager
//
//    private val _generalVolume = MutableLiveData<Int?>()
//    val generalVolume: LiveData<Int?> = _generalVolume
//
//    private val _musicVolume = MutableLiveData<Int?>()
//    val musicVolume: LiveData<Int?> = _musicVolume
//
//    private val _soundVolume = MutableLiveData<Int?>()
//    val soundVolume: LiveData<Int?> = _soundVolume
//
//    init {
//        viewModelScope.launch {
//            dataStore.getGeneralVolume().collect { value ->
//                _generalVolume.postValue(value)
//            }
//
//            dataStore.getMusicVolume().collect { value ->
//                _musicVolume.postValue(value)
//            }
//
//            dataStore.getSoundVolume().collect { value ->
//                _soundVolume.postValue(value)
//            }
//        }
//    }
//
//    fun setGeneralVolume(generalVolume: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStore.setGeneralVolume(generalVolume)
//        }
//    }
//
//    fun setMusicVolume(musicVolume: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStore.setMusicVolume(musicVolume)
//        }
//    }
//
//    fun setSoundVolume(soundVolume: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStore.setSoundVolume(soundVolume)
//        }
//    }
//
//    private fun getGeneralVolume(): Int {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStore.getGeneralVolume().collect() {
//                return@collect
//            }
//        }
//        return 100
//    }
//
//    fun getMusicVolume(): Int {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStore.getMusicVolume().collect() {
//                return@collect
//            }
//        }
//        return 100
//    }
//
//    fun getSoundVolume(): Int {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStore.getSoundVolume().collect() {
//                return@collect
//            }
//        }
//        return 100
//    }

}


