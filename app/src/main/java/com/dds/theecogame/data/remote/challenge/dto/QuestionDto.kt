package com.dds.theecogame.data.remote.challenge.dto

import com.dds.theecogame.domain.model.challenges.Question

data class QuestionDto(
    val challenge_details_id: Int,
    val difficulty: Int,
    val time: Int,

    val question: String,
    val answer: String,
    val option1: String,
    val option2: String,
    val option3: String,

    val clue: String,

    val ods_id: Int
)

fun QuestionDto.toQuestion(): Question {
    return Question(
        id = challenge_details_id,
        difficulty = difficulty,
        time = time,

        question = question,
        answer = answer,
        option1 = option1,
        option2 = option2,
        option3 = option3,

        clue,
        ods_id

    )
}
