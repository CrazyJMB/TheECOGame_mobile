package com.dds.theecogame.domain.factory

import com.dds.theecogame.domain.factory.validators.EmailValidator
import com.dds.theecogame.domain.factory.validators.PasswordValidator
import com.dds.theecogame.domain.factory.validators.UsernameValidator

class ValidatorFactory {
    companion object {
        fun getValidator(type: String): Validator {
            return when (type) {
                "username" -> UsernameValidator()
                "email" -> EmailValidator()
                "password" -> PasswordValidator()
                else -> throw IllegalArgumentException("Tipo de campo no válido")
            }
        }
    }
}