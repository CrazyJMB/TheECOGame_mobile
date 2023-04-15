package com.dds.theecogame.presentation.setting.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dds.theecogame.data.local.DataStoreManager

class SettingViewModel(application: Application) : ViewModel() {

    val dataStore = DataStoreManager(application)

    
    // Logica compleja o datos que deban actualizarle en la vista
    //Creo metodo y paso valor con parámetro

}


