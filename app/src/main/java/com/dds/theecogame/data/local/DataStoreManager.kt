package com.dds.theecogame.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreManager(private val dataStore: DataStore<Preferences>) {

    companion object {
        val userIdKey = stringPreferencesKey("USER_EMAIL")
    }

    suspend fun setUserId(userId: String) {
        dataStore.edit {
            it[userIdKey] = userId
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
}