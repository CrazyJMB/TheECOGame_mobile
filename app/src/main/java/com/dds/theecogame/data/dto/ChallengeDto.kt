package com.dds.theecogame.data.dto

import com.dds.theecogame.domain.model.Challenge

data class ChallengeDto(
    val id: Int,
    val name: String,
    val description: String,
    val type: String
)

fun ChallengeDto.toChallenge(): Challenge {
    return Challenge(
        id = id,
        name = name,
        description = description,
        type = type
    )
}