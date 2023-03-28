package com.dds.theecogame.domain.model

data class Question(
    val id: Int,
    val difficulty: String,
    val time: Int,

    val question: String,
    val answer: String,
    val option1: String,
    val option2: String,
    val option3: String,

    val ods: List<Ods>
)
