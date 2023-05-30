package com.dds.theecogame.data.remote.statistics.dto

import com.dds.theecogame.domain.model.Statistics

data class StatisticsDto(
    val id: Int,
    val user_id: Int,
    val score: Int,

    val win_count: Int,
    val lose_count: Int,
    val quit_count: Int,

    val time_ingame: Int,

    val question_correct_count: Int,
    val question_failed_count: Int,

    val hangman_correct_count: Int,
    val hangman_failed_count: Int,

    val ODS_1: Int,
    val ODS_2: Int,
    val ODS_3: Int,
    val ODS_4: Int,
    val ODS_5: Int,
    val ODS_6: Int,
    val ODS_7: Int,
    val ODS_8: Int,
    val ODS_9: Int,
    val ODS_10: Int,
    val ODS_11: Int,
    val ODS_12: Int,
    val ODS_13: Int,
    val ODS_14: Int,
    val ODS_15: Int,
    val ODS_16: Int,
    val ODS_17: Int,
)

fun StatisticsDto.toStatistics(): Statistics {
    return Statistics(
        user_id,
        win_count,
        lose_count,
        quit_count,
        time_ingame,
        question_correct_count,
        question_failed_count,
        hangman_correct_count,
        hangman_failed_count,
        ODS_1,
        ODS_2,
        ODS_3,
        ODS_4,
        ODS_5,
        ODS_6,
        ODS_7,
        ODS_8,
        ODS_9,
        ODS_10,
        ODS_11,
        ODS_12,
        ODS_13,
        ODS_14,
        ODS_15,
        ODS_16,
        ODS_17
    )
}