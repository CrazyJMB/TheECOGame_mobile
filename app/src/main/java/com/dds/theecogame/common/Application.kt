package com.dds.theecogame.common

import com.dds.theecogame.domain.model.User

internal object Application {
    private var user: User = User(
        0,
        "username",
        "name",
        "surname",
        "password",
        "email",
        "avatar"
    )

    fun getUser() = user

    fun setUser(user: User) {
        this.user = user
    }
}