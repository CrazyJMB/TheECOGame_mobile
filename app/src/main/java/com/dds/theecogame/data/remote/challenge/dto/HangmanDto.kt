package com.dds.theecogame.data.remote.challenge.dto

import com.dds.theecogame.domain.model.challenges.Hangman

data class HangmanDto(
    val id: Int,
    val word: String,
    val difficulty: Int
)

fun HangmanDto.toHangman(): Hangman {
    return Hangman(
        id = id,
        word = word,
        difficulty = difficulty
    )
}



