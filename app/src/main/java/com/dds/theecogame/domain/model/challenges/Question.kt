package com.dds.theecogame.domain.model.challenges

data class Question(
    val id: Int,
    val difficulty: Int,
    val time: Int,

    val question: String,
    val answer: String,
    val option1: String,
    val option2: String,
    val option3: String,

    val clue: String?,

    val ods: Int
)
