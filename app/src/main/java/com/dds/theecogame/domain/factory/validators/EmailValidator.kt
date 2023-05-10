package com.dds.theecogame.domain.factory.validators

import com.dds.theecogame.domain.factory.Validator
import com.dds.theecogame.domain.repository.UserRepository

class EmailValidator : Validator {
    override var error: String
        get(): String {
            return this.error
        }
        set(value) {
            this.error = value
        }

    override var check: Boolean
        get(): Boolean {
            return this.check
        }
        set(value) {
            this.check = value
        }

    override val userRepository: UserRepository
        get(): UserRepository {
            return this.userRepository
        }

    override fun validate(username: String): Boolean {
        return true
    }
}