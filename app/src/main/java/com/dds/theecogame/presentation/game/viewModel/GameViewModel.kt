package com.dds.theecogame.presentation.game.viewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.common.ApiException
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.builder.GameDirector
import com.dds.theecogame.domain.builder.concreteBuilder.QuestionsGameBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class GameViewModel() : ViewModel() {


    private lateinit var game: Game

    private var consolidated: Boolean = false
    fun getConsolidated() = consolidated
    fun setConsolidated(consolidate: Boolean) {
        consolidated = consolidate
    }

    fun createGame(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                game = GameDirector(QuestionsGameBuilder()).buildGameWith10Questions()
            } catch (e: HttpException) {
                viewModelScope.launch(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        e.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

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