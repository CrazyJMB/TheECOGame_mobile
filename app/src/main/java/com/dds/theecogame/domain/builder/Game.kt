package com.dds.theecogame.domain.builder

import com.dds.theecogame.domain.model.Question

class Game {

    val challengesList = mutableListOf<Challenge>()

    open class Challenge {
        data class QuestionModel(val questionModel: Question) : Challenge()
        //data class HangmanBuilder(val handmanModel: Handman) : Challenge()
    }
}