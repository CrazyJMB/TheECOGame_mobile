package com.dds.theecogame.data

import com.dds.theecogame.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {

    @GET("/users")
    suspend fun getUser(): User

    @GET("/users/{id}")
    suspend fun getUser(@Path("username") username: String): User
}