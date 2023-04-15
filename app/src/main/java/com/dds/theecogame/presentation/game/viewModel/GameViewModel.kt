package com.dds.theecogame.presentation.game.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.builder.GameDirector
import com.dds.theecogame.domain.builder.concreteBuilder.QuestionsGameBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GameViewModel() : ViewModel() {

    private lateinit var game: Game

    fun createGame() {
        viewModelScope.launch(Dispatchers.IO) {
            game = GameDirector(QuestionsGameBuilder()).buildGameWith10Questions()
        }
    }


    fun sendResults() {
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

    internal fun hasUserWon(): Boolean {
        //Return false si ha perdido, return true si ha ganado
        var userWon: Boolean = false
        return userWon
    }

    internal fun hasUserAbandoned(): Boolean {
        //Return false si ha abandonado return true si no ha abandonado
        var userAbandoned: Boolean = false
        return userAbandoned
    }

    internal fun hasUserAnsweredAll(): Boolean {
        //Return false si ha finalizado los 10 retos, return true si aun no ha finalizado los 10
        var userAnsweredAll = false
        return userAnsweredAll
    }

    internal fun saveAcumulatedPoints(numberPoints: Int) {
        var acumulatedPoints = numberPoints
    }

}