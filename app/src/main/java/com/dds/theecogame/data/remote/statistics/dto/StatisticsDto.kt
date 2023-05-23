package com.dds.theecogame.data.remote.statistics.dto

import com.dds.theecogame.domain.model.Statistics

data class StatisticsDto(
    val id: Int,

    val user_id: Int,

    val game_count: Int,

    val win_count: Int,
    val lose_count: Int,
    val quit_count: Int,

    val time_ingame: Int,

    val question_correct_count: Int,
    val question_failed_count: Int,

    val hangman_correct_count: Int,
    val hangman_failed_count: Int,

    val ods_knowledge: Int,
)

fun StatisticsDto.toStatistics(): Statistics {
    return Statistics(
        user_id,
        game_count,
        win_count,
        lose_count,
        quit_count,
        time_ingame,
        question_correct_count,
        question_failed_count,
        hangman_correct_count,
        hangman_failed_count,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
        ods_knowledge,
    )
}