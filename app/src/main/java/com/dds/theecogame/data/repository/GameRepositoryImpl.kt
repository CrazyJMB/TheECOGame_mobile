package com.dds.theecogame.data.repository

import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.remote.api.RetrofitInstance
import com.dds.theecogame.data.remote.game.dto.ChallengeDto
import com.dds.theecogame.data.remote.game.dto.GameDto
import com.dds.theecogame.data.remote.game.dto.ScoreDto
import com.dds.theecogame.data.remote.statistics.dto.toStatistics
import com.dds.theecogame.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameRepositoryImpl : GameRepository {

    val api = RetrofitInstance.gameService

    override suspend fun createGame(userId: Int): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())

        val response = api.createGame(GameDto(state = 1, userId))

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.id))
        else
            emit(Resource.Error(response.message()))
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