package com.dds.theecogame.domain

import com.dds.theecogame.domain.model.User

object Application {
    private var user: User? = null

    fun getUser() = user

    fun setUser(user: User?) {
        this.user = user
    }
}