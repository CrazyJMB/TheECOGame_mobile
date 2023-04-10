package com.dds.theecogame.domain.builder

class GameDirector(private val builder: GameBuilder) {

    fun buildGameWith10Questions(): Game {
        builder.setNumberOfChallenges(10)
        TODO("Not yet implemented")
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()


        return builder.buildGame()
    }
}