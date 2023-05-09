package com.dds.theecogame.data.remote.game.api

import com.dds.theecogame.data.remote.game.dto.ChallengeDto
import com.dds.theecogame.data.remote.game.dto.GameDto
import com.dds.theecogame.data.remote.game.dto.ScoreDto
import com.dds.theecogame.data.remote.session.dto.ResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GameApi {

    @POST("/games")
    suspend fun createGame(@Body game: GameDto): Response<ResponseDto>

    @POST("/games/{gameId}/addChallenge")
    suspend fun addChallengeToGame(
        @Path("gameId") gameId: Int,
        @Body challenge: ChallengeDto
    ): Response<ResponseDto>

    @PUT("/games/{gameId}")
    suspend fun updateScore(
        @Path("gameId") gameId: Int,
        @Body score: ScoreDto
    ): Response<ResponseDto>
}