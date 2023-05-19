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

    val ods_knowledge1: Int,
    val ods_knowledge2: Int,
    val ods_knowledge3: Int,
    val ods_knowledge4: Int,
    val ods_knowledge5: Int,
    val ods_knowledge6: Int,
    val ods_knowledge7: Int,
    val ods_knowledge8: Int,
    val ods_knowledge9: Int,
    val ods_knowledge10: Int,
    val ods_knowledge11: Int,
    val ods_knowledge12: Int,
    val ods_knowledge13: Int,
    val ods_knowledge14: Int,
    val ods_knowledge15: Int,
    val ods_knowledge16: Int,
    val ods_knowledge17: Int
)