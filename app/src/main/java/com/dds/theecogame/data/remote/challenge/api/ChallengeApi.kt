package com.dds.theecogame.data.remote.challenge.api

import com.dds.theecogame.data.remote.challenge.dto.HangmanDto
import com.dds.theecogame.data.remote.challenge.dto.QuestionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChallengeApi {

    @GET("/questions/{diff}/{userId}")
    suspend fun getRandomQuestion(
        @Path("diff") diff: Int,
        @Path("userId") userId: Int
    ): Response<QuestionDto>

    @GET("/hangman/{diff}/{userId}")
    suspend fun getRandomHangman(
        @Path("diff") diff: Int,
        @Path("userId") userId: Int
    ): Response<HangmanDto>

}