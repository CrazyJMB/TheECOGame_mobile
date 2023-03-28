package com.dds.theecogame.data.dto

import com.dds.theecogame.domain.model.Ods
import com.dds.theecogame.domain.model.Question

data class QuestionDto(
    val id: Int,
    val difficulty: String,
    val time: Int,

    val question: String,
    val answer: String,
    val option1: String,
    val option2: String,
    val option3: String,

    val ods: List<OdsDto>
)

fun QuestionDto.toQuestion(): Question {
    return Question(
        id = id,
        difficulty = difficulty,
        time = time,
        question = question,
        answer = answer,
        option1 = option1,
        option2 = option2,
        option3 = option3,
        ods = ods.map { it.toOds() }
    )
}
