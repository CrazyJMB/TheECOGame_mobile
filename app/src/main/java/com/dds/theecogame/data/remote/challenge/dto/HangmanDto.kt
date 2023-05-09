package com.dds.theecogame.data.remote.challenge.dto

import com.dds.theecogame.domain.model.challenges.Hangman

data class HangmanDto(
    val challenge_details_id: Int,
    val word: String,
    val time: Int,
    val difficulty: Int
)

fun HangmanDto.toHangman(): Hangman {
    return Hangman(
        id = challenge_details_id,
        word = word,
        difficulty = difficulty
    )
}



