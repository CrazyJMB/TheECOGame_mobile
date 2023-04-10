package com.dds.theecogame.domain.builder

import com.dds.theecogame.common.RetrofitInstance
import com.dds.theecogame.domain.model.Question

class GameDirector(private val builder: GameBuilder) {

    suspend fun buildGameWith10Questions(): Game {
        builder.setNumberOfChallenges(10)
        TODO("Not yet implemented")
//        builder.addQuestionChallenge(RetrofitInstance.challengeService.getQuestions())
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()
//        builder.addQuestionChallenge()


        return builder.buildGame()
    }
}