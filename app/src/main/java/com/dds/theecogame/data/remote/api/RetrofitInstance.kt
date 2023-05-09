package com.dds.theecogame.data.remote.api

import com.dds.theecogame.data.remote.challenge.api.ChallengeApi
import com.dds.theecogame.data.remote.game.api.GameApi
import com.dds.theecogame.data.remote.session.api.SessionApi
import com.dds.theecogame.data.remote.statistics.api.StatisticsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.jorma28j.upv.edu.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(ErrorInterceptor())
                    .build()
            )
            .build()
    }

    val userService: SessionApi = retrofit.create(SessionApi::class.java)
    val gameService: GameApi = retrofit.create(GameApi::class.java)
    val challengeService: ChallengeApi = retrofit.create(ChallengeApi::class.java)
    val statisticsService: StatisticsApi = retrofit.create(StatisticsApi::class.java)
}