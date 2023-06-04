package com.dds.theecogame.presentation.auth.register.viewModel

import androidx.lifecycle.ViewModel
import com.dds.theecogame.common.Resource
import com.dds.theecogame.domain.factory.ValidatorFactory
import com.dds.theecogame.domain.model.Response
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import kotlinx.coroutines.flow.Flow

class RegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val usernameValidator = ValidatorFactory.getValidator("username")
    private val emailValidator = ValidatorFactory.getValidator("email")
    private val passwordValidator = ValidatorFactory.getValidator("password")

    fun checkUsername(username: String): Boolean {
        return usernameValidator.validate(username)
    }

    fun getUsernameError(): String {
        return usernameValidator.getError()
    }

    fun checkEmail(email: String): Boolean {
        return emailValidator.validate(email)
    }

    fun getEmailError(): String {
        return emailValidator.getError()
    }

    fun checkPassword(password: String): Boolean {
        return passwordValidator.validate(password)
    }

    fun getPasswordError(): String {
        return passwordValidator.getError()
    }

    suspend fun createUser(
        username: String,
        name: String,
        surname: String,
        email: String,
        password: String
    ): Flow<Resource<Response>> {
        return userRepository.createUser(username, name, surname, email, password)
    }
}