package com.dds.theecogame.data.remote.statistics.api

import com.dds.theecogame.data.remote.session.dto.ResponseDto
import com.dds.theecogame.data.remote.statistics.dto.PositionDto
import com.dds.theecogame.data.remote.statistics.dto.RankingDto
import com.dds.theecogame.data.remote.statistics.dto.StatisticsDto
import com.dds.theecogame.data.remote.statistics.dto.TimeDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface StatisticsApi {

    @GET("/statistics/{userId}")
    suspend fun getStatistics(@Path("userId") userId: Int): Response<StatisticsDto>

    @PUT("/statistics/{userId}/win")
    suspend fun updateWinStatistics(@Path("userId") userId: Int): Response<ResponseDto>

    @PUT("/statistics/{userId}/lose")
    suspend fun updateLoseStatistics(@Path("userId") userId: Int): Response<ResponseDto>

    @PUT("/statistics/{userId}/quit")
    suspend fun updateQuitStatistics(@Path("userId") userId: Int): Response<ResponseDto>

    @PUT("/statistics/{userId}/time")
    suspend fun registerTimeStatistics(
        @Path("userId") userId: Int,
        @Body time: TimeDto
    ): Response<ResponseDto>

    @PUT("/statistics/{userId}/questionCorrect")
    suspend fun registerQuestionCorrectStatistics(@Path("userId") userId: Int): Response<ResponseDto>

    @PUT("/statistics/{userId}/questionFailed")
    suspend fun registerQuestionFailedStatistics(@Path("userId") userId: Int): Response<ResponseDto>

    @PUT("/statistics/{userId}/hangmanCorrect")
    suspend fun registerHangmanCorrectStatistics(@Path("userId") userId: Int): Response<ResponseDto>

    @PUT("/statistics/{userId}/hangmanFailed")
    suspend fun registerHangmanFailedStatistics(@Path("userId") userId: Int): Response<ResponseDto>


    // Ranking
    @GET("/rankings")
    suspend fun getRanking(): Response<List<RankingDto>>

    @GET("/rankings/{userId}")
    suspend fun getRankingFromUser(@Path("userId") userId: Int): Response<PositionDto>
}