package com.dds.theecogame.presentation.auth.login.viewModel

import androidx.lifecycle.ViewModel
import com.dds.theecogame.common.Resource
import com.dds.theecogame.domain.model.Response
import com.dds.theecogame.domain.model.User
import com.dds.theecogame.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class LoginActivityViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    suspend fun checkPassword(email: String, password: String): Flow<Resource<Response>> {
        return userRepository.checkPassword(email, password)
    }


    suspend fun login(email: String): Flow<Resource<User>> {
        return userRepository.getUser(email)
    }

}