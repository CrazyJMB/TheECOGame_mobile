package com.dds.theecogame.domain.memento

class SettingsCareTaker {
    private var settingMemento: Memento? = null

    fun saveMemento(memento: Memento) {
        settingMemento = memento
    }

    fun restoreMemento(): Memento? {
        return settingMemento
    }
}