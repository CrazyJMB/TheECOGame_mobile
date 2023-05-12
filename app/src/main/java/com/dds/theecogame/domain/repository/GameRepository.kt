package com.dds.theecogame.domain.repository

import com.dds.theecogame.common.Resource
import kotlinx.coroutines.flow.Flow


interface GameRepository {

    suspend fun createGame(userId: Int): Flow<Resource<Int>>

    suspend fun addChallengeToGame(
        gameId: Int,
        challengeId: Int,
        challengeType: String
    )

    suspend fun updateScore(gameId: Int, score: Int)
}