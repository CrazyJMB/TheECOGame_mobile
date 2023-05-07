package com.dds.theecogame.domain.builder.concreteBuilder

import com.dds.theecogame.data.remote.api.RetrofitInstance
import com.dds.theecogame.data.remote.challenge.dto.toHangman
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.builder.GameBuilder
import kotlinx.coroutines.runBlocking

class HangmanGameBuilder : GameBuilder {

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
                game.challengesList[order] = Game.Challenge.HangmanModel(
                    RetrofitInstance.challengeService.getRandomHangman(
                        (1..5).random(),
                        game.userId
                    ).toHangman()
                )
            }
        }
    }
}