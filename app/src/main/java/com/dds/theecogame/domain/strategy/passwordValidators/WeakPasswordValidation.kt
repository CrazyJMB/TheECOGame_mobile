package com.dds.theecogame.domain.strategy.passwordValidators

import com.dds.theecogame.domain.strategy.PasswordValidationType

class WeakPasswordValidation : PasswordValidationType {
    private var error: String = String()

    override fun validatePassword(password: String): Boolean {

        if (password.isEmpty()) return true
        if (password.length < 8) {
            error = "Debe contener al menos 8 caracteres"
            return false
        }

        return true
    }

    override fun getError(): String {
        return this.error
    }
}