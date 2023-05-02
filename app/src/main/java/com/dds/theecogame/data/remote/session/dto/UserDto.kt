package com.dds.theecogame.data.remote.session.dto

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