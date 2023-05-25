package com.dds.theecogame.data.repository

import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.remote.api.RetrofitInstance
import com.dds.theecogame.data.remote.statistics.dto.TimeDto
import com.dds.theecogame.data.remote.statistics.dto.toRankingUser
import com.dds.theecogame.data.remote.statistics.dto.toStatistics
import com.dds.theecogame.domain.model.Statistics
import com.dds.theecogame.domain.model.ranking.RankingUser
import com.dds.theecogame.domain.repository.StatisticsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StatisticsRepositoryImpl : StatisticsRepository {

    private val api = RetrofitInstance.statisticsService

    override suspend fun getStatistics(userId: Int): Flow<Resource<Statistics>> = flow {
        emit(Resource.Loading())

        val response = api.getStatistics(userId)

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toStatistics()))
        else
            emit(Resource.Error(response.message()))
    }

    override suspend fun registerWinStatistic(userId: Int) {
        api.updateWinStatistics(userId)
    }

    override suspend fun registerLoseStatistic(userId: Int) {
        api.updateLoseStatistics(userId)
    }

    override suspend fun registerQuitStatistic(userId: Int) {
        api.updateQuitStatistics(userId)
    }

    override suspend fun registerTimeStatistics(userId: Int, time: Int) {
        api.registerTimeStatistics(userId, TimeDto(time))
    }

    override suspend fun registerQuestionCorrectStatistics(userId: Int) {
        api.registerQuestionCorrectStatistics(userId)
    }

    override suspend fun registerQuestionFailedStatistics(userId: Int) {
        api.registerQuestionFailedStatistics(userId)
    }

    override suspend fun registerHangmanCorrectStatistics(userId: Int) {
        api.registerHangmanCorrectStatistics(userId)
    }

    override suspend fun registerHangmanFailedStatistics(userId: Int) {
        api.registerHangmanFailedStatistics(userId)
    }

    // Ranking
    override suspend fun getRanking(): Flow<Resource<List<RankingUser>>> = flow {
        emit(Resource.Loading())

        val response = api.getRanking()

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.map { it.toRankingUser() }))
        else
            emit(Resource.Error(response.message()))
    }

    override suspend fun getUserRanking(userId: Int): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())

        val response = api.getRankingFromUser(userId)

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.posicion))
        else
            emit(Resource.Error(response.message()))
    }

}