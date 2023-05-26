package com.dds.theecogame.domain.model.challenges

import com.dds.theecogame.data.remote.challenge.dto.HangmanDto

data class Hangman(
    val id: Int,
    val difficulty: Int,
    val time: Int,

    val word: String,

    val clue: String,

    val ods: Int

)
