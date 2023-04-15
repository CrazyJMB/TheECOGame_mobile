package com.dds.theecogame.common

import com.dds.theecogame.data.requests.challenge.api.ChallengeApi
import com.dds.theecogame.data.requests.session.api.SessionApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userService = retrofit.create(SessionApi::class.java)
    val challengeService = retrofit.create(ChallengeApi::class.java)
}