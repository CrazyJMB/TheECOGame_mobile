package com.dds.theecogame.domain.model.challenges

import com.dds.theecogame.data.remote.challenge.dto.HangmanDto

data class Hangman(
    val id: Int,
    val word: String,
    val difficulty: Int
)
