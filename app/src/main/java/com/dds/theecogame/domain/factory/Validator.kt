package com.dds.theecogame.domain.factory

import com.dds.theecogame.domain.repository.UserRepository

interface Validator {
    var error: String
    var check: Boolean
    val userRepository: UserRepository

    fun validate(input: String): Boolean
}