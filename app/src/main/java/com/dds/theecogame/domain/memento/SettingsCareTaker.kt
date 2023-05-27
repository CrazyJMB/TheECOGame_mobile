package com.dds.theecogame.domain.memento

class SettingsCareTaker {
    private var settingsCareTaker: Settings? = null
    private var isStateSaved = false

    fun saveState(settings: Settings) {
        if (!isStateSaved) {
            this.settingsCareTaker = settings.copy()
            isStateSaved = true
        }
    }

    fun restoreState(): Settings? {
        return settingsCareTaker
    }
}