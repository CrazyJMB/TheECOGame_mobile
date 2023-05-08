package com.dds.theecogame.common

import com.dds.theecogame.domain.model.User

object Application {
    private lateinit var user: User

    fun getUser() = user

    fun setUser(user: User) {
        this.user = user
    }
}