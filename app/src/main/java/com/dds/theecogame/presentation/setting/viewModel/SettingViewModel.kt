package com.dds.theecogame.presentation.setting.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.data.local.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingViewModel(application: Application) : ViewModel() {

    val dataStore = DataStoreManager(application)

    fun sta() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getMusicVolume().collect() {
                
            }
        }
    }


    // Logica compleja o datos que deban actualizarle en la vista
    //Creo metodo y paso valor con parámetro

}


