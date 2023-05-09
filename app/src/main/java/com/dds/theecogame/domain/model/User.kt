package com.dds.theecogame.domain.model

data class User(
    val id: Int,
    val username: String,
    val name: String?,
    val surname: String?,
    val password: String,
    val email: String,
    val avatar: String?
)