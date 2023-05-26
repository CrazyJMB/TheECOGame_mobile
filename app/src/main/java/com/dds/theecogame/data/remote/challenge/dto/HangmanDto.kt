package com.dds.theecogame.data.remote.challenge.dto

import com.dds.theecogame.domain.model.challenges.Hangman

data class HangmanDto(
    val challenge_details_id: Int,
    val difficulty: Int,
    val time: Int,

    val word: String,

    val clue: String,

    val ods_id: Int
)

fun HangmanDto.toHangman(): Hangman {
    return Hangman(
        challenge_details_id,
        difficulty,
        time,

        word,
        clue,
        ods_id
    )
}



