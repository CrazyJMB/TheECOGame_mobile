package com.dds.theecogame.domain.builder

import com.dds.theecogame.domain.model.Question
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GameTest {

    private lateinit var game: Game

    @Before
    fun onBefore() {
        game = Game()
    }

    @Test
    fun getChallengesList() {
    }

    @Test
    fun `test sorting challenges by difficulty`() {

        val challengesList = mutableMapOf<Int, Game.Challenge>()

        // create challenges with different difficulties
        val challenge1 = Game.Challenge.QuestionModel(mock(Question::class.java).apply {
            `when`(this.difficulty).thenReturn(3)
        })
        val challenge2 = Game.Challenge.QuestionModel(mock(Question::class.java).apply {
            `when`(this.difficulty).thenReturn(5)
        })
        val challenge3 = Game.Challenge.QuestionModel(mock(Question::class.java).apply {
            `when`(this.difficulty).thenReturn(2)
        })


        // create a mutable map to store the mock challenges and add them to it
        challengesList[1] = challenge1
        challengesList[2] = challenge2
        challengesList[3] = challenge3

        // set up the mock game object
        val game = mock(Game::class.java)
        `when`(game.challengesList).thenReturn(challengesList)

        // call the function to be tested
        game.sortChallengesByDifficulty()

        val expectedOrder = listOf(3, 1, 2)
        assertEquals(expectedOrder, challengesList.keys.toList())

    }

    @Test
    fun getNextChallenge() {
    }

    @Test
    fun deleteFirstChallenge() {
    }
}