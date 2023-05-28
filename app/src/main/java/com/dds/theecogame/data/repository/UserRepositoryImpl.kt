package com.dds.theecogame.data.repository

import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.remote.api.RetrofitInstance
import com.dds.theecogame.data.remote.session.dto.input.PasswordDto
import com.dds.theecogame.data.remote.session.dto.input.UserCreationDto
import com.dds.theecogame.data.remote.session.dto.toResponse
import com.dds.theecogame.data.remote.session.dto.toUser
import com.dds.theecogame.domain.model.Response
import com.dds.theecogame.domain.model.User
import com.dds.theecogame.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UserRepositoryImpl : UserRepository {

    private val api = RetrofitInstance.userService

    override suspend fun checkUsername(username: String): Flow<Resource<Response>> = flow {

        emit(Resource.Loading())

        val response = api.checkUsername(username)

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toResponse()))
        else
            emit(Resource.Error(response.message()))
    }

    override suspend fun checkEmail(email: String): Flow<Resource<Response>> = flow {

        emit(Resource.Loading())

        val response = api.checkEmail(email)

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toResponse()))
        else
            emit(Resource.Error(response.message()))
    }

    override suspend fun checkPassword(
        email: String,
        password: String
    ): Flow<Resource<Response>> = flow {

        emit(Resource.Loading())

        val response = api.checkPassword(email, PasswordDto(password))

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toResponse()))
        else
            emit(Resource.Error(response.message()))
    }

    override suspend fun getUser(email: String): Flow<Resource<User>> = flow {

        emit(Resource.Loading())

        val response = api.getUser(email)

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toUser()))
        else
            emit(Resource.Error(response.message()))
    }

    override suspend fun createUser(
        username: String,
        name: String,
        surname: String,
        email: String,
        password: String
    ): Flow<Resource<Response>> = flow {

        emit(Resource.Loading())

        val response = api.createUser(UserCreationDto(email, name, password, surname, username))

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toResponse()))
        else
            emit(Resource.Error(response.message()))
    }

    override suspend fun updateUser(
        userId: Int,
        username: String,
        name: String,
        surname: String,
        email: String,
        password: String
    ): Flow<Resource<Response>> = flow {

        emit(Resource.Loading())

        val response =
            api.updateUser(userId, UserCreationDto(email, name, password, surname, username))

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toResponse()))
        else
            emit(Resource.Error(response.message()))
    }

    override suspend fun updateAvatar(userId: Int, fileUrl: String): Flow<Resource<Response>> =
        flow {

            emit(Resource.Loading())

            val file = File(fileUrl)
            val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
            val image = MultipartBody.Part.createFormData("image", file.name, requestFile)

            val response = api.updateAvatar(userId, image)

            if (response.isSuccessful)
                emit(Resource.Success(response.body()!!.toResponse()))
            else
                emit(Resource.Error(response.message()))
        }
}