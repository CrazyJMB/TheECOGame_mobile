package com.dds.theecogame.presentation.game.viewModel

import android.content.Context
import android.widget.Toast
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.R
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.builder.GameDirector
import com.dds.theecogame.domain.builder.concreteBuilder.QuestionGameBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class GameViewModel : ViewModel() {

    private val _gameLiveData = MutableLiveData<Game>()
    val gameLiveData: LiveData<Game> = _gameLiveData

    private lateinit var mediaPlayer: MediaPlayer

    private var consolidated: Boolean = false
    private var secondChance: Boolean = false
    private var gameEnded: Boolean = false
    private var gamePoints: Int = 0
    private var gameStatus: Int = 0
    private var consolidatedPoints: Int = 0
    private var questionNumber: Int = 1
    private var timeStart: Long = 0L
    private var timeEnd: Long = 0L

    fun getConsolidated() = consolidated
    fun getSecondChance() = secondChance
    fun getGameEnded() = gameEnded
    fun getPoints() = gamePoints
    fun getGameStatus() = gameStatus
    fun getQuestionNumber() = questionNumber
    fun getConsolidatedPoints() = consolidatedPoints
    fun getTimeStart() = timeStart
    fun getTimeEnd() = timeEnd

    fun setConsolidated(consolidate: Boolean) {
        consolidated = consolidate
    }

    fun setGameEnded() {
        gameEnded = true
    }

    fun setConsolidatedPoints() {
        consolidatedPoints = gamePoints
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

    fun setTimeStart() {
        timeStart = System.currentTimeMillis()
    }

    fun setTimeEnd() {
        timeEnd = System.currentTimeMillis()
    }

    fun setGameStatus(status: Int) {
        // 0 -> Lost
        // 1 -> Abandoned
        // 2 -> Victory
        gameStatus = status
    }

    fun createGame(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val game = GameDirector(QuestionGameBuilder()).construct()
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

    fun getResults(): List<Int> {
        var summaryStats = mutableListOf<Int>()

        // Lost
        if (gameStatus == 0) {
            gamePoints = 0
        }
        // Abandoned
        if (gameStatus == 1) {
            gamePoints = consolidatedPoints
        }

        val timePlayed = (timeEnd - timeStart) / 1000

        summaryStats.add(timePlayed.toInt())
        summaryStats.add(gamePoints)
        summaryStats.add(questionNumber)

        return summaryStats
    }

    fun startMusic(context: Context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.fondo)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    fun pauseMusic() {
        mediaPlayer.pause()
    }

    fun releaseMusic() {
        mediaPlayer.release()
    }

    fun resumeMusic() {
        mediaPlayer.start()
    }
}