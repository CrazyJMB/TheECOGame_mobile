package com.dds.theecogame.domain.model

data class User(
    val contraseña: String,
    val correo: String,
    val id_usuario: Int,
    val puntosAcumulados: Any,
    val usuario: String
)