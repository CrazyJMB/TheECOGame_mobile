package com.dds.theecogame.domain.repository


interface GameRepository {

    suspend fun createGame(userId: Int)

    suspend fun addChallengeToGame(
        userId: Int,
        gameId: Int,
        challengeId: Int,
        challengeType: String
    )

    suspend fun updateScore(gameId: Int, score: Int)
}