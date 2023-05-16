package com.dds.theecogame.domain.factory.validators

import com.dds.theecogame.R
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.domain.factory.Validator
import com.dds.theecogame.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking

class EmailValidator : Validator {
    private var error: String = String()
    private var check: Boolean = true
    private val userRepository: UserRepository = UserRepositoryImpl()

    override fun getError(): String {
        return this.error
    }

    override fun validate(email: String): Boolean {
        //check principal
        val patron = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

        if (email.isEmpty()) return true

        if (!patron.matches(email)) {
            error = "Formato de correo no válido"
            return false
        }
        runBlocking {
            userRepository.checkEmail(email).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        check = true
                    }
                    is Resource.Error -> {
                        error = "Email no disponible"
                        //error = it.message.toString()
                        check = false
                    }
                }
            }
        }
        return true
    }
}