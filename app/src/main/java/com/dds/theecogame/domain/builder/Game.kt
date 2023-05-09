package com.dds.theecogame.domain.builder

import com.dds.theecogame.domain.model.challenges.Hangman
import com.dds.theecogame.domain.model.challenges.Question

class Game {

    var userId: Int = -1
    var gameId: Int = -1
    var challengesNumber: Int = 0
    val challengesList = mutableMapOf<Int, Challenge>()

    open class Challenge {
        data class QuestionModel(val questionModel: Question) : Challenge()
        data class HangmanModel(val hangmanModel: Hangman) : Challenge()
    }

    fun sortChallengesByDifficulty(): Boolean {
        val sortedChallenges = challengesList.values.sortedBy { challenge ->
            when (challenge) {
                is Challenge.QuestionModel -> challenge.questionModel.difficulty
                is Challenge.HangmanModel -> challenge.hangmanModel.difficulty

                else -> 10
            }
        }

        challengesList.clear()

        sortedChallenges.forEachIndexed { index, challenge ->
            challengesList[index + 1] = challenge
        }
        return true
    }

    fun getNextChallenge(): Challenge? {
        return challengesList.values.firstOrNull()
    }

    fun deleteFirstChallenge(): Challenge? {
        val firstChallenge = challengesList.values.firstOrNull()
        if (firstChallenge != null)
            challengesList.remove(challengesList.entries.first().key)
        return firstChallenge
    }

}