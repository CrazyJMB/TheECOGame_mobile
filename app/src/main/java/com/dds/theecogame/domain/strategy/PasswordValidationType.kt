package com.dds.theecogame.domain.strategy

interface PasswordValidationType {
    fun validatePassword(password: String): Boolean
    fun getError(): String
}