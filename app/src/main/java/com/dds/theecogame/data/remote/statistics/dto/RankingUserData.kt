package com.dds.theecogame.data.remote.statistics.dto

import com.dds.theecogame.domain.model.ranking.CurrentUserRanking

data class RankingUserData(
    val position: Int,
    val score: Int
)

fun RankingUserData.toCurrentUserRanking(): CurrentUserRanking {
    return CurrentUserRanking(position, score)
}