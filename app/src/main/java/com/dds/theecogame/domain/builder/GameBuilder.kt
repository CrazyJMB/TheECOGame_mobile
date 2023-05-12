package com.dds.theecogame.domain.builder

interface GameBuilder {
    fun buildGame(): Game
    fun setUser(userId: Int)
    fun setNumberOfChallenges(numberOfChallenges: Int)
    fun addChallenges()
}
