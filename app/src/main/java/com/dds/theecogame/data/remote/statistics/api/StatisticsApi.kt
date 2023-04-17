package com.dds.theecogame.data.remote.statistics.api

import com.dds.theecogame.data.remote.statistics.dto.StatisticsDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StatisticsApi {

    @GET("/statistics/{userId}")
    suspend fun getStatistics(@Path("userId") userId: Int): StatisticsDto

    @POST("/statistics/{userId}")
    suspend fun createStatistics(@Path("userId") userId: Int)

    @PUT("/statistics/{userId}")
    suspend fun updateStatistics(@Path("userId") userId: Int)
}