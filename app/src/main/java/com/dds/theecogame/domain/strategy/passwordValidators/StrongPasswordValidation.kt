package com.dds.theecogame.domain.strategy.passwordValidators

import com.dds.theecogame.domain.strategy.PasswordValidationType

class StrongPasswordValidation : PasswordValidationType {
    private var error: String = String()

    override fun validatePassword(password: String): Boolean {
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

    override fun getError(): String {
        return this.error
    }
}