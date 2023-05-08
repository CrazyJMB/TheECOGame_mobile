package com.dds.theecogame.presentation.mainScreen.viewModel

import androidx.lifecycle.ViewModel
import com.dds.theecogame.data.local.DataStoreManager

class MainScreenViewModel() : ViewModel() {

    suspend fun login(
        email: String,
        password: String,
        dataStoreManager: DataStoreManager
    ): Boolean {
        //TODO: Revisar formato email
//
//        val responseDto = RetrofitInstance.userService.checkEmail(EmailDto(email))
//
//        if (responseDto.message.equals("Email exists")){
//            val player = RetrofitInstance.userService.getUser(EmailDto(email))
//            if (player.password.equals(password)){
//                dataStoreManager.setUserId(player.email)
//                return true
//            }
//            return false
//        } else {
//            return false
//        }
        return true
    }

}


