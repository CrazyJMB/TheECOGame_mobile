package com.dds.theecogame.domain.builder.concreteBuilder

import com.dds.theecogame.data.remote.api.RetrofitInstance
import com.dds.theecogame.data.remote.challenge.dto.toQuestion
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.builder.GameBuilder
import kotlinx.coroutines.runBlocking

class QuestionGameBuilder : GameBuilder {

    private val game = Game()

    override fun buildGame(): Game {
        require(game.challengesList.size == game.challengesNumber) {
            "Game must have exactly ${game.challengesNumber} challenges"
        }
        return game
    }

    override fun setUser(userId: Int) {
        game.userId = userId
    }

    override fun setNumberOfChallenges(numberOfChallenges: Int) {
        game.challengesNumber = numberOfChallenges
    }

    override fun addChallenges() {
        runBlocking {
            (1..game.challengesNumber).forEach { order ->
                game.challengesList[order] = Game.Challenge.QuestionModel(
                    RetrofitInstance.challengeService.getRandomQuestion(
                        (1..5).random(),
                        game.userId
                    ).toQuestion()
                )
            }
        }
    }
}