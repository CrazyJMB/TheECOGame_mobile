package com.dds.theecogame.domain.builder.concreteBuilder

import com.dds.theecogame.common.Resource
import com.dds.theecogame.domain.repository.ChallengesRepository
import com.dds.theecogame.domain.repository.GameRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.anyInt
import org.mockito.Matchers.eq
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class HangmanGameBuilderTest {

    @Mock
    private lateinit var challengesRepository: ChallengesRepository

    @Mock
    private lateinit var gameRepository: GameRepository

    private lateinit var builder: HangmanGameBuilder

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        builder = HangmanGameBuilder(challengesRepository, gameRepository)
    }

    @Test
    fun testBuildGame() {
        // Arrange
        builder.setUser(1)
        builder.setNumberOfChallenges(3)

        // Mock the repository calls and expected responses
        builder.addChallenges()

        // Act
        val game = builder.buildGame()

        // Assert
        assertNotNull(game)
        assertEquals(1, game.userId)
        assertEquals(3, game.challengesNumber)
        assertEquals(123, game.gameId)
        assertEquals(3, game.challengesList.size)
    }


}