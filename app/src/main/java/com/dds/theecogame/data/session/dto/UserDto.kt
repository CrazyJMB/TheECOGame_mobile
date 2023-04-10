package com.dds.theecogame.data.session.dto

import com.dds.theecogame.domain.model.User

data class UserDto(
    val id: Int,
    val username: String,
    val name: String,
    val surname: String,
    val password: String,
    val email: String,
    val score: Int,
    val time: Int
)

fun UserDto.toUser(): User {
    return User(
        id = id,
        username = username,
        password = password,
        email = email,
        score = score
    )
}
