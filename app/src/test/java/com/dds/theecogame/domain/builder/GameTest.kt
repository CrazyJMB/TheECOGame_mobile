package com.dds.theecogame.domain.builder

import com.dds.theecogame.domain.builder.Game.Challenge
import com.dds.theecogame.domain.model.challenges.Hangman
import com.dds.theecogame.domain.model.challenges.Question
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class GameTest {

    private lateinit var game: Game

    private val question = Question(
        1,
        2,
        30,
        "Question",
        "Answer",
        "Option1",
        "Option3",
        "option3",
        null,
        1
    )

    private val hangman = Hangman(
        1,
        4,
        30,
        "Word",
        "Answer",
        1
    )

    @Before
    fun setup() {
        game = Game()
    }

    @Test
    fun sortChallengesByDifficulty_emptyList_returnTrue() {
        val result = game.sortChallengesByDifficulty()

        assertEquals(true, result)
        assertEquals(0, game.challengesList.size)
    }

    @Test
    fun sortChallengesByDifficulty_sortsChallenges() {
        // Modelos
        val questionMock1 = Question(
            1,
            2,
            30,
            "Question",
            "Answer",
            "Option1",
            "Option3",
            "option3",
            clue = null,
            ods = 1
        )

        val questionMock2 = Question(
            1,
            3,
            30,
            "Question",
            "Answer",
            "Option1",
            "Option3",
            "option3",
            clue = null,
            ods = 1
        )

        val hangmanMock1 = Hangman(
            1,
            4,
            30,
            "Word",
            "Answer",
            1
        )


        // Add challenges to the game
        game.challengesList[1] = Challenge.QuestionModel(questionMock1)
        game.challengesList[2] = Challenge.HangmanModel(hangmanMock1)
        game.challengesList[3] = Challenge.QuestionModel(questionMock2)

        // Sort challenges by difficulty
        val result = game.sortChallengesByDifficulty()

        assertEquals(true, result)

        // Check the order of challenges
        val sortedChallenges = game.challengesList.values.toList()
        assertEquals(questionMock1, (sortedChallenges[0] as Challenge.QuestionModel).questionModel)
        assertEquals(questionMock2, (sortedChallenges[1] as Challenge.QuestionModel).questionModel)
        assertEquals(hangmanMock1, (sortedChallenges[2] as Challenge.HangmanModel).hangmanModel)
    }

    @Test
    fun getNextChallenge_emptyList_returnNull() {
        // Arrange
        val emptyChallengesList = mutableMapOf<Int, Game.Challenge>()
        game.challengesList.putAll(emptyChallengesList)

        // Act
        val result = game.getNextChallenge()

        // Assert
        assertNull(result)
    }

    @Test
    fun getNextChallenge_nonEmptyList_returnFirstChallenge() {
        // Arrange
        val challenge1 = Challenge.QuestionModel(question)
        val challenge2 = Challenge.HangmanModel(hangman)
        val challengesList = mutableMapOf<Int, Game.Challenge>(1 to challenge1, 2 to challenge2)
        game.challengesList.putAll(challengesList)

        // Act
        val result = game.getNextChallenge()

        // Assert
        assertEquals(challenge1, result)
    }

    @Test
    fun getNextChallenge_singleChallengeInList_returnChallenge() {
        // Arrange
        val challenge = Game.Challenge.QuestionModel(question)

        game.challengesList[1] = challenge

        // Act
        val result = game.getNextChallenge()

        // Assert
        assertEquals(challenge, result)
    }

    @Test
    fun getNextChallenge_multipleChallengesInList_returnFirstChallenge() {
        // Arrange
        val challenge1 = Game.Challenge.QuestionModel(question)
        val challenge2 = Game.Challenge.HangmanModel(hangman)
        val challenge3 = Game.Challenge.QuestionModel(question)

        game.challengesList[1] = challenge1
        game.challengesList[2] = challenge2
        game.challengesList[3] = challenge3

        // Act
        val result = game.getNextChallenge()

        // Assert
        assertEquals(challenge1, result)
    }

    @Test
    fun getNextChallenge_addChallengeAfterGetting_returnNextChallenge() {
        // Arrange
        val challenge1 = Game.Challenge.QuestionModel(question)
        val challenge2 = Game.Challenge.HangmanModel(hangman)

        game.challengesList[1] = challenge1

        // Act
        val result1 = game.getNextChallenge()

        game.challengesList[2] = challenge2

        val result2 = game.getNextChallenge()

        // Assert
        assertEquals(challenge1, result1)
        assertEquals(challenge1, result2)
    }

    @Test
    fun deleteFirstChallenge_nonEmptyList_removeAndReturnFirstChallenge() {
        // Arrange
        val challenge1 = Game.Challenge.QuestionModel(question)
        val challenge2 = Game.Challenge.HangmanModel(hangman)

        game.challengesList[1] = challenge1
        game.challengesList[2] = challenge2

        // Act
        val result = game.deleteFirstChallenge()

        // Assert
        assertEquals(challenge1, result)
        assertNull(game.challengesList[1])
        assertEquals(challenge2, game.challengesList[2])
    }

    @Test
    fun deleteFirstChallenge_emptyList_returnNull() {
        // Arrange

        // Act
        val result = game.deleteFirstChallenge()

        // Assert
        assertNull(result)
    }

}