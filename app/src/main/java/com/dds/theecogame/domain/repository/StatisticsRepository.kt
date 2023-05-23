package com.dds.theecogame.domain.repository

import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.remote.session.dto.ResponseDto
import com.dds.theecogame.domain.model.Statistics
import com.dds.theecogame.domain.model.User
import kotlinx.coroutines.flow.Flow

interface StatisticsRepository {

    suspend fun getStatistics(userId: Int): Flow<Resource<Statistics>>

    suspend fun registerWinStatistic(userId: Int)
    suspend fun registerLoseStatistic(userId: Int)
    suspend fun registerQuitStatistic(userId: Int)

    suspend fun registerTimeStatistics(userId: Int, time: Int)

    suspend fun registerQuestionCorrectStatistics(userId: Int)
    suspend fun registerQuestionFailedStatistics(userId: Int)

    suspend fun registerHangmanCorrectStatistics(userId: Int)
    suspend fun registerHangmanFailedStatistics(userId: Int)

    // Ranking
    suspend fun getRanking(): Flow<Resource<List<User>>>

    suspend fun getUserRanking(userId: Int): Flow<Resource<Int>>
}