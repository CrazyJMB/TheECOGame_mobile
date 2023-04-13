package com.dds.theecogame.data.session.manager.api

import com.dds.theecogame.data.session.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SessionApi {

    @GET("/users")
    suspend fun getUser(): UserDto

    @GET("/users/{id}")
    suspend fun getUser(@Path("username") username: String): UserDto
}