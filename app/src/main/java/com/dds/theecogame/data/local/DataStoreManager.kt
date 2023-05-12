package com.dds.theecogame.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreManager(private val dataStore: DataStore<Preferences>) {

    companion object {
        val userIdKey = stringPreferencesKey("USER_ID")
        val generalVolumeKey = intPreferencesKey("GENERAL_VOLUME")
        val musicVolumeKey = intPreferencesKey("MUSIC_VOLUME")
        val soundVolumeKey = intPreferencesKey("SOUND_VOLUME")
    }

    suspend fun setUserId(userId: String) {
        dataStore.edit {
            it[userIdKey] = userId
        }
    }

    suspend fun setGeneralVolume(volume: Int) {
        dataStore.edit {
            it[generalVolumeKey] = volume
        }
    }

    suspend fun setMusicVolume(volume: Int) {
        dataStore.edit {
            it[musicVolumeKey] = volume
        }
    }

    suspend fun setSoundVolume(volume: Int) {
        dataStore.edit {
            it[soundVolumeKey] = volume
        }
    }

    suspend fun getUserId(): Flow<String> {
        return dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map {
                it[userIdKey] ?: String()
            }
    }

    suspend fun getGeneralVolume(): Flow<Int> {
        return dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map {
                it[generalVolumeKey] ?: 100
            }
    }

    suspend fun getMusicVolume(): Flow<Int> {
        return dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map {
                it[musicVolumeKey] ?: 100
            }
    }

    suspend fun getSoundVolume(): Flow<Int> {
        return dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map {
                it[soundVolumeKey] ?: 100
            }
    }
}