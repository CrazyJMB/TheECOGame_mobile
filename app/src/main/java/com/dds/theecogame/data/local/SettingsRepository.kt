package com.dds.theecogame.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.dds.theecogame.domain.memento.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SettingsRepository(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val GENERAL_VOLUME_KEY = intPreferencesKey("generalVolume")
        private val MUSIC_VOLUME_KEY = intPreferencesKey("musicVolume")
        private val SOUND_VOLUME_KEY = intPreferencesKey("soundVolume")

        private val BRIGHTNESS_KEY = intPreferencesKey("brightness")
    }

    suspend fun saveSettings(settings: Settings) {
        dataStore.edit { preferences ->
            preferences[GENERAL_VOLUME_KEY] = settings.volume
            preferences[MUSIC_VOLUME_KEY] = settings.music
            preferences[SOUND_VOLUME_KEY] = settings.sound
            preferences[BRIGHTNESS_KEY] = settings.brightness
        }
    }

    val settingsFlow: Flow<Settings> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val volume = preferences[GENERAL_VOLUME_KEY] ?: 100
            val music = preferences[MUSIC_VOLUME_KEY] ?: 100
            val sound = preferences[SOUND_VOLUME_KEY] ?: 100
            val brightness = preferences[BRIGHTNESS_KEY] ?: 100
            Settings(volume, music, sound, brightness)
        }
}