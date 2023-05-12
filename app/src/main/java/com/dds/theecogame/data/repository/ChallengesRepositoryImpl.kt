package com.dds.theecogame.data.repository

import com.dds.theecogame.data.remote.api.RetrofitInstance
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.remote.challenge.dto.toHangman
import com.dds.theecogame.data.remote.challenge.dto.toQuestion
import com.dds.theecogame.data.remote.session.dto.toResponse
import com.dds.theecogame.domain.model.challenges.Hangman
import com.dds.theecogame.domain.model.challenges.Question
import com.dds.theecogame.domain.repository.ChallengesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChallengesRepositoryImpl : ChallengesRepository {

    private val api = RetrofitInstance.challengeService

    override suspend fun getQuestion(difficulty: Int, userId: Int): Flow<Resource<Question>> =
        flow {
            emit(Resource.Loading())

            val response = api.getRandomQuestion(difficulty, userId)

            if (response.isSuccessful)
                emit(Resource.Success(response.body()!!.toQuestion()))
            else
                emit(Resource.Error(response.message()))
        }

    override suspend fun getHangman(difficulty: Int, userId: Int): Flow<Resource<Hangman>> =
        flow {
            emit(Resource.Loading())

            val response = api.getRandomHangman(difficulty, userId)

            if (response.isSuccessful)
                emit(Resource.Success(response.body()!!.toHangman()))
            else
                emit(Resource.Error(response.message()))
        }
}