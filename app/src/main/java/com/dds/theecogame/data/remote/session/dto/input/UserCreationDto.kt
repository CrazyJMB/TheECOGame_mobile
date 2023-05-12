package com.dds.theecogame.data.remote.session.dto.input

data class UserCreationDto(
    val email: String,
    val name: String,
    val password: String,
    val surname: String,
    val username: String
)