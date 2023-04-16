package com.dds.theecogame.domain.builder

import com.dds.theecogame.common.RetrofitInstance
import com.dds.theecogame.data.remote.challenge.dto.toQuestion

class GameDirector(private val builder: GameBuilder) {

    suspend fun buildGameWith10Questions(): Game {
        builder.setNumberOfChallenges(10)
        (1..10).forEach { order ->
            builder.addQuestionChallenge(
                order,
                RetrofitInstance.challengeService.getQuestionByDifficulty((1..4).random())
                    .toQuestion()
            )
        }
        return builder.buildGame()
    }
}