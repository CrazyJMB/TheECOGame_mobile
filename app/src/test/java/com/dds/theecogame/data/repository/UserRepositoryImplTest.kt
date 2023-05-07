package com.dds.theecogame.data.repository

import com.dds.theecogame.data.remote.api.common.Resource
import com.dds.theecogame.data.remote.session.dto.ResponseDto
import com.dds.theecogame.data.remote.session.dto.toResponse
import com.dds.theecogame.domain.repository.UserRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserRepositoryImplTest {

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = UserRepositoryImpl()
    }

    @Test
    fun `checkUsername should emit success Resource if response is successful`() = runBlocking {
        // Given
        val username = "test"
        val response = Response.success(ResponseDto("Username available"))

        // When
        val result = userRepository.checkUsername(username).toList()

        // Then
        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(response.body()?.toResponse(), (result[1] as Resource.Success).data)
    }

    @Test
    fun `checkUsername should emit error Resource if response is not successful`() =
        runBlocking {
            // Given
            val username = "john123"
            val response =
                Response.error<ResponseDto>(400, ResponseBody.create(null, "Bad Request"))

            // When
            val result = userRepository.checkUsername(username).toList()

            // Then
            assertEquals(2, result.size)
            assertTrue(result[0] is Resource.Loading)
            assertTrue(result[1] is Resource.Error)
            assertEquals(response.message(), (result[1] as Resource.Error).message)
        }

    @Test
    fun `checkEmail should emit success Resource if response is successful`() = runBlocking {
        // Given
        val email = "email@email.com"
        val response = Response.success(ResponseDto("Email available"))

        // When
        val result = userRepository.checkEmail(email).toList()

        // Then
        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(response.body()?.toResponse(), (result[1] as Resource.Success).data)
    }

    @Test
    fun `checkEmail should emit error Resource if response is not successful`() = runBlocking {
        // Given
        val email = "john@example.com"
        val response = Response.error<ResponseDto>(400, ResponseBody.create(null, "Bad Request"))

        // When
        val result = userRepository.checkEmail(email).toList()

        // Then
        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
        assertEquals(response.message(), (result[1] as Resource.Error).message)
    }
}