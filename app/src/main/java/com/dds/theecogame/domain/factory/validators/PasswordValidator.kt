package com.dds.theecogame.domain.factory.validators

import com.dds.theecogame.R
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.domain.factory.Validator
import com.dds.theecogame.domain.repository.UserRepository

class PasswordValidator : Validator {
    private var error: String = String()

    override fun getError(): String {
        return this.error
    }

    override fun validate(password: String): Boolean {
        //check principal
        val lowercase = Regex(".*[a-z].*")
        val uppercase = Regex(".*[A-Z].*")
        val number = Regex(".*[\\d].*")

        if (password.isEmpty()) return true
        if (password.length < 8) {
            error = "Debe contener al menos 8 caracteres"
            return false
        }
        if (!lowercase.matches(password)) {
            error = "Debe contener al menos una minúscula"
            return false
        }
        if (!uppercase.matches(password)) {
            error = "Debe contener al menos una mayúscula"
            return false
        }
        if (!number.matches(password)) {
            error = "Debe contener al menos un número"
            return false
        }
        //check con llamada a api NO es necesaria

        return true
    }
}