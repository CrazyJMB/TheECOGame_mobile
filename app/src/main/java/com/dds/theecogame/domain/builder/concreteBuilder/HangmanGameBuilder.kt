package com.dds.theecogame.domain.builder.concreteBuilder

import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.remote.api.RetrofitInstance
import com.dds.theecogame.data.remote.challenge.dto.toHangman
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.builder.GameBuilder
import com.dds.theecogame.domain.repository.ChallengesRepository
import kotlinx.coroutines.runBlocking

class HangmanGameBuilder(
    private val challengesRepository: ChallengesRepository
) : GameBuilder {

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
                challengesRepository.getHangman((1..5).random(), game.userId).collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            game.challengesList[order] = Game.Challenge.HangmanModel(it.data!!)
                        }

                        is Resource.Error -> {}
                    }
                }
            }
        }
    }
}