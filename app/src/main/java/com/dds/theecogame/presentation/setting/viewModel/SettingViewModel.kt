package com.dds.theecogame.presentation.setting.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.data.local.SettingsRepository
import com.dds.theecogame.domain.memento.Memento
import com.dds.theecogame.domain.memento.SettingsCareTaker
import com.dds.theecogame.domain.model.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {

    private val careTaker: SettingsCareTaker = SettingsCareTaker()

    // Utilizamos MutableLiveData para exponer las configuraciones al observador
    val settingsLiveData: MutableLiveData<Settings> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // Cargamos las configuraciones desde el flujo y las asignamos al LiveData
            settingsRepository.settingsFlow.collect { settings ->
                settingsLiveData.postValue(settings)

                // Guardamos el memento solo después de cargar las configuraciones
                careTaker.saveMemento(
                    Memento(
                        settings.volume,
                        settings.sound,
                        settings.music,
                        settings.brightness
                    )
                )
            }
        }
    }

    fun updateGeneralVolume(volume: Int) {
        val currentSettings = settingsLiveData.value
        currentSettings?.let {
            val updatedSettings = it.copy(volume = volume)
            settingsLiveData.postValue(updatedSettings)
        }
    }

    fun updateSoundVolume(sound: Int) {
        val currentSettings = settingsLiveData.value
        currentSettings?.let {
            val updatedSettings = it.copy(sound = sound)
            settingsLiveData.postValue(updatedSettings)
        }
    }

    fun updateMusicVolume(music: Int) {
        val currentSettings = settingsLiveData.value
        currentSettings?.let {
            val updatedSettings = it.copy(music = music)
            settingsLiveData.postValue(updatedSettings)
        }
    }

    fun updateBrightness(brightness: Int) {
        val currentSettings = settingsLiveData.value
        currentSettings?.let {
            val updatedSettings = it.copy(brightness = brightness)
            settingsLiveData.postValue(updatedSettings)
        }
    }

    fun restorePreviousSettings() {
        val restoreMemento = careTaker.restoreMemento()
        restoreMemento?.let {
            val restoredSettings = Settings(
                it.volume,
                it.sound,
                it.music,
                it.brightness
            )
            settingsLiveData.postValue(restoredSettings)
        }
    }

    suspend fun saveSettings() {
        val currentSettings = settingsLiveData.value ?: return
        settingsRepository.saveSettings(currentSettings)
    }
}



