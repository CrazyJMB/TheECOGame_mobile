package com.dds.theecogame.data.service

import com.dds.theecogame.data.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.jorma28j.upv.edu.es")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userService = retrofit.create(UserApiService::class.java)
}