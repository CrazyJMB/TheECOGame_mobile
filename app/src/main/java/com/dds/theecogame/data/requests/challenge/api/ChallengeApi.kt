package com.dds.theecogame.data.requests.challenge.api

import com.dds.theecogame.data.requests.challenge.dto.QuestionDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ChallengeApi {

    @GET("/questions/{diff}/random")
    suspend fun getQuestionByDifficulty(@Path("diff") diff: Int): QuestionDto

    @GET("/questions/{diff}/{userId}")
    suspend fun getRandomQuestion(@Path("diff") diff: Int, @Path("userId") userId: Int): QuestionDto

}