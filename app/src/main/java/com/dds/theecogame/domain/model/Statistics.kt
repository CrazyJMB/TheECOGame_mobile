package com.dds.theecogame.domain.model

data class Statistics(
    val user_id: Int,

    val game_count: Int,

    val win_count: Int,
    val lose_count: Int,
    val quit_count: Int,

    val time_ingame: Int,

    val question_correct_count: Int,
    val question_failed_count: Int,

    val hangman_correct_count: Int,
    val hangman_failed_count: Int,

    val ods_knowledge: Int,
)