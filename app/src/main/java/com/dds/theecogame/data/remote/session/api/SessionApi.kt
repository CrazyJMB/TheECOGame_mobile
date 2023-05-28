package com.dds.theecogame.data.remote.session.api

import com.dds.theecogame.data.remote.session.dto.ResponseDto
import com.dds.theecogame.data.remote.session.dto.UserDto
import com.dds.theecogame.data.remote.session.dto.input.PasswordDto
import com.dds.theecogame.data.remote.session.dto.input.UserCreationDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface SessionApi {

    @GET("/users/checkUsername")
    suspend fun checkUsername(@Query("username") username: String): Response<ResponseDto>

    @GET("/users/checkEmail")
    suspend fun checkEmail(@Query("email") email: String): Response<ResponseDto>

    @POST("/users/checkPassword")
    suspend fun checkPassword(
        @Query("email") email: String,
        @Body password: PasswordDto
    ): Response<ResponseDto>

    @GET("/users")
    suspend fun getUser(@Query("email") email: String): Response<UserDto>

    @POST("/users")
    suspend fun createUser(@Body user: UserCreationDto): Response<ResponseDto>

    @PUT("/users/{userId}")
    suspend fun updateUser(
        @Path("userId") userId: Int,
        @Body user: UserCreationDto
    ): Response<ResponseDto>

    @Multipart
    @POST("/users/{userId}/avatar")
    fun updateAvatar(
        @Path("userId") userId: Int,
        @Part image: MultipartBody.Part
    ): Response<ResponseDto>
}