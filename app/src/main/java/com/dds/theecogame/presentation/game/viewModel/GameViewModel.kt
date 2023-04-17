package com.dds.theecogame.presentation.game.viewModel

import android.content.Context
import android.widget.Toast
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.builder.GameDirector
import com.dds.theecogame.domain.builder.concreteBuilder.QuestionsGameBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class GameViewModel : ViewModel() {

    private val _gameLiveData = MutableLiveData<Game>()
    val gameLiveData: LiveData<Game> = _gameLiveData

    private var consolidated: Boolean = false
    private var secondChance: Boolean = false
    private var gamePoints: Int = 0
    private var questionNumber: Int = 1

    fun getConsolidated() = consolidated
    fun getSecondChance() = secondChance
    fun getPoints() = gamePoints
    fun getQuestionNumber() = questionNumber

    fun setConsolidated(consolidate: Boolean) {
        consolidated = consolidate
    }

    fun setSecondChange(change: Boolean) {
        secondChance = change
    }

    fun addPoints(points: Int) {
        gamePoints += points
    }

    fun nextQuestionNumber() {
        questionNumber += 1
    }

    fun createGame(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val game = GameDirector(QuestionsGameBuilder()).buildGameWith10Questions()
                game.sortChallengesByDifficulty()
                _gameLiveData.postValue(game)
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

    internal fun resetGameStats(sharedPref: SharedPreferences) {
        val editor = sharedPref.edit()
        val currentDate = System.currentTimeMillis()

        editor.putString("gameStatus", "Defeat")
        editor.putInt("points", 0)
        editor.putInt("consolidatedPoints", 0)
        editor.putLong("timeStarted", currentDate)
        editor.putLong("timeEnded", 0)
        editor.putInt("numberChallengesAnswered", 0)
        editor.apply()
    }

    internal fun getResults(sharedPref: SharedPreferences): List<Int> {
        var summaryStats = mutableListOf<Int>()

        var points = 0
        var dataStart = sharedPref.getLong("timeStarted", 0)
        var dataEnd = sharedPref.getLong("timeEnded", 0)
        var numberChallenge = sharedPref.getInt("numberChallengesAnswered", 0)

        var gameStatus = sharedPref.getString("gameStatus", "")
        if (gameStatus == "Victory") {
            points = sharedPref.getInt("points", 0)
        }
        if (gameStatus == "Abandoned") {
            points = sharedPref.getInt("consolidatedPoints", 0)
        }

        val timePlayed = (dataEnd - dataStart) / 1000
        summaryStats.add(timePlayed.toInt())
        summaryStats.add(points)
        summaryStats.add(numberChallenge)

        return summaryStats
    }

    internal fun hasUserWon(sharedPref: SharedPreferences): Boolean {
        var userWon = sharedPref.getString("gameStatus", "")
        return userWon == "Victory"
    }

    internal fun hasUserAbandoned(sharedPref: SharedPreferences): Boolean {
        var userWon = sharedPref.getString("gameStatus", "")
        return userWon == "Abandoned"
    }

    internal fun addConsolidatePoints(sharedPref: SharedPreferences) {
        var accumulatedPoints = sharedPref.getInt("points", 0)
        val editor = sharedPref.edit()
        editor.putInt("consolidatedPoints", accumulatedPoints)
        editor.apply()
    }

    internal fun getConsolidatePoints(sharedPref: SharedPreferences): Int {
        var numberPoints = sharedPref.getInt("consolidatedPoints", 0)
        return numberPoints
    }

    internal fun changeGameStatus(sharedPref: SharedPreferences, value: String) {
        if (value == "Defeat" || value == "Victory" || value == "Abandoned") {
            val editor = sharedPref.edit()
            editor.putString("gameStatus", value)
            editor.apply()
        }
    }


    internal fun setTimeEnded(sharedPref: SharedPreferences) {
        val currentDate = System.currentTimeMillis()
        val editor = sharedPref.edit()
        editor.putLong("timeEnded", currentDate)
        editor.apply()
    }


    internal fun getTimeEnded(sharedPref: SharedPreferences): Long {
        var timeEnded = sharedPref.getLong("timeEnded", 0)
        return timeEnded
    }

    internal fun askTipeOds() {
        //TODO
    }
}