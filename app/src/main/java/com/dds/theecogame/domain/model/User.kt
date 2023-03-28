package com.dds.theecogame.domain.model

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val score: Int
)