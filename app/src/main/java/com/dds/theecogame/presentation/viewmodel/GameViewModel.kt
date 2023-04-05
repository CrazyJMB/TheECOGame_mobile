package com.dds.theecogame.presentation.viewmodel

import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    /*A IMPLEMENTAR TODO*/
    internal fun sendResults() {
        //Funcionalidad a implementar mas tarde cuando haya participantes, y ahi guardamos sus stats
    }

    internal fun getResults(): List<Int> {
        //Conseguir Tiempo Jugado en la Partida, Puntos Obtenidos y Num Preguntas respondidas
        //He pensado que directamente que haya un fichero .txt donde hay pongamos eso
        //Y a partir de ese fichero obtener esos datos
        var summaryStats = mutableListOf<Int>()
        /*IMPLEMENTAR*/
        return summaryStats
    }

    internal fun askIfUserWon(): Boolean {
        //Return false si ha perdido, return true si ha ganado
        var userWon: Boolean = false
        return userWon
    }

    internal fun askIfUserHasAbandoned(): Boolean {
        //Return false si ha abandonado return true si no ha abandonado
        var userAbandoned: Boolean = false
        return userAbandoned
    }

    internal fun askIfUserHasntFinished(): Boolean {
        //Return false si ha finalizado los 10 retos, return true si aun no ha finalizado los 10
        var userHasntFinished = true
        return userHasntFinished
    }
}