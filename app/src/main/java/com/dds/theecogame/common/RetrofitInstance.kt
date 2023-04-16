package com.dds.theecogame.common

import com.dds.theecogame.data.remote.challenge.api.ChallengeApi
import com.dds.theecogame.data.remote.session.api.SessionApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(ErrorInterceptor())
                    .build()
            )
            .build()
    }

    val userService = retrofit.create(SessionApi::class.java)
    val challengeService = retrofit.create(ChallengeApi::class.java)
}