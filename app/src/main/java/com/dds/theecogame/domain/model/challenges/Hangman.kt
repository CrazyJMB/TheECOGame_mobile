package com.dds.theecogame.domain.model.challenges

import com.dds.theecogame.data.remote.challenge.dto.HangmanDto

data class Hangman(
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
