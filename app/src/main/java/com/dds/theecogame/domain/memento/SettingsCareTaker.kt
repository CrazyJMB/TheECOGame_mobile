package com.dds.theecogame.domain.memento

class SettingsCareTaker {
    private var settings: Settings? = null

    fun saveState(settings: Settings) {
        this.settings = settings
    }

    fun restoreState(): Settings? {
        return settings
    }
}