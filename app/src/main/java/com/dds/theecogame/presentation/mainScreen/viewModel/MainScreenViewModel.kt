package com.dds.theecogame.presentation.mainScreen.viewModel

import androidx.lifecycle.ViewModel
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.repository.UserRepository

class MainScreenViewModel() : ViewModel() {

    fun endSession() {
        Application.setUser(null)
        //FIXME(Vaciar el dataStore el email guardado)
    }

    private val userRepository: UserRepository = UserRepositoryImpl()

}


