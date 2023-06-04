package com.dds.theecogame.domain.strategy

interface PasswordValidationType {
    fun validatePassword(): Boolean
}