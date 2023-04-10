package com.dds.theecogame.data.challenge.manager.api

import com.dds.theecogame.data.challenge.dto.QuestionDto
import com.dds.theecogame.domain.model.Question
import retrofit2.http.GET

interface ChallengeManager {

    @GET("/questions")
    suspend fun getQuestions(): Question


}