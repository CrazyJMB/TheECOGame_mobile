package com.dds.theecogame.data.remote.statistics.dto

import com.dds.theecogame.domain.model.ranking.RankingUser

data class RankingDto(
    val avatar: String?,
    val score: Int,
    val username: String
)

fun RankingDto.toRankingUser(): RankingUser {
    return RankingUser(
        avatar, score, username
    )
}
