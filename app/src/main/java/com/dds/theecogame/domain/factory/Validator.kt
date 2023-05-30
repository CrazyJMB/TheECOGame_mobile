package com.dds.theecogame.domain.factory

interface Validator {
    fun validate(input: String): Boolean
    fun getError(): String
}