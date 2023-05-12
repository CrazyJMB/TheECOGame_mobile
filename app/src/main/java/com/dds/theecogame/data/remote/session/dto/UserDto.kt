package com.dds.theecogame.data.remote.session.dto

import com.dds.theecogame.domain.model.User

data class UserDto(
    val avatar: String,
    val creation_date: Any,
    val email: String,
    val id: Int,
    val modify_date: String,
    val name: String,
    val password: String,
    val surname: String,
    val username: String
)

fun UserDto.toUser(): User {
    return User(
        id, username, name, surname, password, email, avatar
    )
}