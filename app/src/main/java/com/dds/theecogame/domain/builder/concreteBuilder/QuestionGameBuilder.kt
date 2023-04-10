package com.dds.theecogame.domain.builder.concreteBuilder

import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.builder.GameBuilder
import com.dds.theecogame.domain.model.Question

class QuestionsGameBuilder : GameBuilder {

    private val game: Game = Game()

    override fun setNumberOfChallenges(numberOfChallenges: Int) {
        require(numberOfChallenges == 10) { "This builder only supports 10 challenges" }
    }

    override fun addQuestionChallenge(question: Question) {
        val challenge = Game.Challenge.QuestionModel(question)
        game.challengesList.add(challenge)
    }

    override fun buildGame(): Game {
        require(game.challengesList.size == 10) { "Game must have exactly 10 challenges" }
        return game
    }
}
