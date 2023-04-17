package com.dds.theecogame.domain.builder

import com.dds.theecogame.domain.model.Question

interface GameBuilder {
    fun buildGame(): Game
    fun setNumberOfChallenges(numberOfChallenges: Int)
    fun addQuestionChallenge(order: Int, question: Question)
    //fun addHangmanChallenge(word: String): GameBuilder
}
