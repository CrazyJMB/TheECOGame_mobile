package com.dds.theecogame.domain.builder

import com.dds.theecogame.domain.model.Question

class Game {

    val challengesList = mutableMapOf<Int, Challenge>()

    open class Challenge {
        data class QuestionModel(val questionModel: Question) : Challenge()
        //data class HangmanBuilder(val handmanModel: Handman) : Challenge()
    }

    fun sortChallengesByDifficulty() {
        val sortedChallenges = challengesList.values.sortedBy { challenge ->
            if (challenge is Challenge.QuestionModel) {
                challenge.questionModel.difficulty
            } else {
                10 // Si no es un Challenge.QuestionModel, lo dejamos al final
            }
        }

        challengesList.clear()
        sortedChallenges.forEachIndexed { index, challenge ->
            challengesList[index + 1] = challenge
        }
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