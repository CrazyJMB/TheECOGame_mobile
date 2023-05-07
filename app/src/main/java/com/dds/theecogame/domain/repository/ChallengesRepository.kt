package com.dds.theecogame.domain.repository

import com.dds.theecogame.data.remote.api.common.Resource
import com.dds.theecogame.domain.model.challenges.Hangman
import com.dds.theecogame.domain.model.challenges.Question
import kotlinx.coroutines.flow.Flow


interface ChallengesRepository {
    suspend fun getQuestion(difficulty: Int, userId: Int): Flow<Resource<Question>>
    suspend fun getHangman(difficulty: Int, userId: Int): Flow<Resource<Hangman>>
}