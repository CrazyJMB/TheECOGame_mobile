package com.dds.theecogame.data.repository

import com.dds.theecogame.data.remote.api.RetrofitInstance
import com.dds.theecogame.data.remote.game.dto.ChallengeDto
import com.dds.theecogame.data.remote.game.dto.GameDto
import com.dds.theecogame.data.remote.game.dto.ScoreDto
import com.dds.theecogame.domain.repository.GameRepository

class GameRepositoryImpl : GameRepository {

    val api = RetrofitInstance.gameService

    override suspend fun createGame(userId: Int) {
        api.createGame(GameDto(state = 1, userId))
    }

    override suspend fun addChallengeToGame(
        userId: Int,
        gameId: Int,
        challengeId: Int,
        challengeType: String
    ) {
        api.addChallengeToGame(gameId, ChallengeDto(challengeId, challengeType, gameId))
    }

    override suspend fun updateScore(gameId: Int, score: Int) {
        api.updateScore(gameId, ScoreDto(score))
    }


}