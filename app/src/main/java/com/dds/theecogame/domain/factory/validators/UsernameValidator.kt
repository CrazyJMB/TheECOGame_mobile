package com.dds.theecogame.domain.factory.validators

import com.dds.theecogame.R
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.domain.factory.Validator
import com.dds.theecogame.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class UsernameValidator : Validator {
    private var error: String = String()
    private var check: Boolean = true
    private val userRepository: UserRepository = UserRepositoryImpl()

    override fun getError(): String {
        return this.error
    }

    override fun validate(username: String): Boolean {
        //check principal
        if (username.length > 20) {
            error = "Introduzca menos de 20 caracteres"
            return false
        }
        //check con llamada a api

        runBlocking(Dispatchers.IO) {
            userRepository.checkUsername(username).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        check = true
                    }

                    is Resource.Error -> {
                        error = if (it.message.isNullOrEmpty()) {
                            "Usuario no disponible"
                        } else {
                            it.message
                        }
                        //error =
                        check = false
                    }
                }
            }
        }

        return check
    }
}