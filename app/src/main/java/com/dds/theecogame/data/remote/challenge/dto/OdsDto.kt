package com.dds.theecogame.data.remote.challenge.dto

import com.dds.theecogame.domain.model.Ods

data class OdsDto(
    val id: Int,
    val name: String,
    val objective: String
)

fun OdsDto.toOds(): Ods {
    return Ods(
        id = id,
        name = name,
        objective = objective
    )
}