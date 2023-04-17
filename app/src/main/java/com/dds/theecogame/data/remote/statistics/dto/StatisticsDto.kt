package com.dds.theecogame.data.remote.statistics.dto

import com.dds.theecogame.domain.model.Statistics


data class StatisticsDto(
    val id: Int,
    val lose_count: Any,
    val quit_count: Any,
    val user_id: Int,
    val win_conunt: Any
)

fun StatisticsDto.toStatistics(): Statistics {
    return Statistics(
        user_id = user_id,
        win_conunt = win_conunt,
        lose_count = lose_count,
        quit_count = quit_count
    )
}