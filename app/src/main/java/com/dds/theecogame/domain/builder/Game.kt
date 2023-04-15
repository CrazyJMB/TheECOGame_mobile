package com.dds.theecogame.domain.builder

import com.dds.theecogame.domain.model.Question

class Game {

    val challengesList = mutableMapOf<Int, Challenge>()

    open class Challenge {
        data class QuestionModel(val questionModel: Question) : Challenge()
        //data class HangmanBuilder(val handmanModel: Handman) : Challenge()
    }

    fun sortChallengesByDifficulty() {
        val sortedMap = challengesList.toSortedMap(compareBy {
            challengesList[it]?.let { challenge ->
                when (challenge) {
                    is Challenge.QuestionModel -> challenge.questionModel.difficulty
                    //is Challenge.HangmanBuilder -> challenge.handmanModel.difficulty
                    else -> 10
                }
            }
        })
        challengesList.clear()
        challengesList.putAll(sortedMap)
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