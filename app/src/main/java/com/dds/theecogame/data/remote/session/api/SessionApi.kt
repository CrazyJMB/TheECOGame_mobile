package com.dds.theecogame.data.remote.session.api

import com.dds.theecogame.data.remote.session.dto.ResponseDto
import com.dds.theecogame.data.remote.session.dto.UserDto
import com.dds.theecogame.data.remote.session.dto.input.EmailDto
import com.dds.theecogame.data.remote.session.dto.input.PasswordDto
import com.dds.theecogame.data.remote.session.dto.input.UserCreationDto
import com.dds.theecogame.data.remote.session.dto.input.UsernameDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SessionApi {

    @GET("/users/checkUsername")
    suspend fun checkUsername(@Body username: UsernameDto): ResponseDto

    @GET("/users/checkEmail")
    suspend fun checkEmail(@Body email: EmailDto): ResponseDto

    @GET("/users/{userId}/password")
    suspend fun checkPassword(@Path("userId") userId: Int, @Body password: PasswordDto): ResponseDto

    @GET("/users")
    suspend fun getUser(@Body email: EmailDto): UserDto

    @POST("/users")
    suspend fun createUser(@Body user: UserCreationDto): ResponseDto
}