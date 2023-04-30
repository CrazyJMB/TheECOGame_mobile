package com.dds.theecogame.domain.builder

import com.dds.theecogame.common.RetrofitInstance
import com.dds.theecogame.data.remote.challenge.dto.toQuestion

class GameDirector(private val builder: GameBuilder) {

    fun construct(): Game {
        builder.setUser(1)  //FIXME(Get the current user of the app)
        builder.setNumberOfChallenges(10)
        builder.addChallenges()
        return builder.buildGame()
    }
}