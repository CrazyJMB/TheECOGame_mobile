package com.dds.theecogame.data.session.manager.impl

import com.dds.theecogame.data.session.dto.UserDto
import com.dds.theecogame.data.session.manager.api.SessionApiService

class SessionApiServiceImpl : SessionApiService {

    override suspend fun getUser(): UserDto {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(username: String): UserDto {
        TODO("Not yet implemented")
    }
}