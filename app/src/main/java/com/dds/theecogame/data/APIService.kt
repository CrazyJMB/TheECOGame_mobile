package com.dds.theecogame.data

import com.dds.theecogame.domain.model.User
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("/users")
    suspend fun getUser() : Call<User>
}