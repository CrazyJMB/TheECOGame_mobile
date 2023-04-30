package com.dds.theecogame.presentation

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.dds.theecogame.data.local.DataStoreManager

class MyApp : Application() {

    val dataStoreManager: DataStoreManager by lazy {
        DataStoreManager(dataStore)
    }

    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_pref")
}