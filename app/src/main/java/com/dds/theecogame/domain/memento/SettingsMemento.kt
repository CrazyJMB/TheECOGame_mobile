package com.dds.theecogame.domain.memento

class SettingsMemento(private val settings: Settings) {

    fun getSettings(): Settings {
        return settings
    }
}