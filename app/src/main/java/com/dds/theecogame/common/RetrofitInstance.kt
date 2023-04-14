package com.dds.theecogame.common

//import com.dds.theecogame.data.challenge.manager.api.ChallengeManager
//import com.dds.theecogame.data.session.manager.api.SessionApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.jorma28j.upv.edu.es")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //val userService = retrofit.create(SessionApiService::class.java)
    //val challengeService = retrofit.create(ChallengeManager::class.java)
}