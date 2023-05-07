package com.dds.theecogame.domain.repository

import com.dds.theecogame.data.remote.api.common.Resource
import com.dds.theecogame.domain.model.Response
import com.dds.theecogame.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UserRepository {

    suspend fun checkUsername(username: String): Flow<Resource<Response>>

    suspend fun checkEmail(email: String): Flow<Resource<Response>>

    suspend fun checkPassword(userId: Int, password: String): Flow<Resource<Response>>

    suspend fun getUser(email: String): Flow<Resource<User>>

    suspend fun createUser(
        username: String,
        name: String,
        surname: String,
        email: String,
        password: String
    ): Flow<Resource<Response>>

    suspend fun updateUser(
        username: String,
        name: String,
        surname: String,
        email: String,
        password: String
    ): Flow<Resource<Response>>

    suspend fun updateAvatar(file: File): Flow<Resource<Response>>
}