package com.dds.theecogame.domain.factory.validators

import com.dds.theecogame.R
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.domain.factory.Validator
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.domain.strategy.passwordValidators.StrongPasswordValidation
import com.dds.theecogame.domain.strategy.passwordValidators.WeakPasswordValidation

class PasswordValidator : Validator {
    private var error: String = String()
    private var validationType: Boolean = false

    override fun getError(): String {
        return this.error
    }

    override fun validate(password: String): Boolean {
        if (validationType) {
            val strongValidationType = StrongPasswordValidation()
            val result = strongValidationType.validatePassword(password)
            error = strongValidationType.getError()
            return result

        } else {
            val weakValidationType = WeakPasswordValidation()
            val result = weakValidationType.validatePassword(password)
            error = weakValidationType.getError()
            return result
        }
    }

    override fun setPasswordValidation(type: Boolean) {
        validationType = type
    }
}