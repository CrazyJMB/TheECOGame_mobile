package com.dds.theecogame.domain.builder

import com.dds.theecogame.domain.Application

class GameDirector(private val builder: GameBuilder) {

    fun construct(): Game {
        builder.setUser(Application.getUser()!!.id)
        builder.setNumberOfChallenges(10)
        builder.addChallenges()
        return builder.buildGame()
    }
}