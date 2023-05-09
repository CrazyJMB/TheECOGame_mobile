package com.dds.theecogame.presentation.mainScreen.viewModel

import androidx.lifecycle.ViewModel
import com.dds.theecogame.data.local.DataStoreManager
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.domain.repository.UserRepository

class MainScreenViewModel() : ViewModel() {

    private val userRepository: UserRepository = UserRepositoryImpl()

    suspend fun login(
        email: String,
        password: String,
        dataStoreManager: DataStoreManager
    ): Boolean {
        //TODO Mirar a ver como arreglar esta vaina
        //TODO Se ha cambiado de RetrofitInstance a UserRepository, pero no me deja ver sus atributos y tal
//        val responseDto = userRepository.checkEmail(email)
//
//        if (responseDto.equals("Email exists")){
//            val player = userRepository.getUser(email)
//            if (player.password.equals(password)){
//                dataStoreManager.setUserId(player.id)
//                return true
//            }
//
//            return false
//
//        } else {
//            return false
//        }
        return false
    }

}


