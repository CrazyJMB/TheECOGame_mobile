package com.dds.theecogame.presentation.game.viewModel

import android.content.Context
import android.widget.Toast
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.R
import com.dds.theecogame.data.repository.ChallengesRepositoryImpl
import com.dds.theecogame.data.repository.GameRepositoryImpl
import com.dds.theecogame.data.repository.StatisticsRepositoryImpl
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.builder.Game
import com.dds.theecogame.domain.builder.GameDirector
import com.dds.theecogame.domain.builder.concreteBuilder.QuestionHangmanGameBuilder
import com.dds.theecogame.domain.repository.ChallengesRepository
import com.dds.theecogame.domain.repository.GameRepository
import com.dds.theecogame.domain.repository.StatisticsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class GameViewModel : ViewModel() {

    private val _gameLiveData = MutableLiveData<Game>()
    val gameLiveData: LiveData<Game> = _gameLiveData

    private val challengesRepository: ChallengesRepository = ChallengesRepositoryImpl()
    private val gameRepository: GameRepository = GameRepositoryImpl()
    private val statisticsRepository: StatisticsRepository = StatisticsRepositoryImpl()

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
    private var usedHelp: Boolean = false
    var currentChallengeClue: String? = null

    private val _numberUsedHelp = MutableLiveData<Int>()
    var numberUsedHelp: LiveData<Int> = _numberUsedHelp

    private val _btnPressed = MutableLiveData<Char>()
    var btnPressed: LiveData<Char> = _btnPressed
    private val _visibleLetters = MutableLiveData<MutableList<Char>>()
    var visibleLetters: LiveData<MutableList<Char>> = _visibleLetters

    private val _inFragmentChallenges = MutableLiveData<Boolean>()
    var inFragmentChallenges: LiveData<Boolean> = _inFragmentChallenges

    private var countDownTimer: CountDownTimer? = null
    private var secondsLeft: Long = 0
    val countdownLiveData = MutableLiveData<Long>()


    fun getConsolidated() = consolidated
    fun getSecondChance() = secondChance
    fun getGameEnded() = gameEnded
    fun getPoints() = gamePoints
    fun getGameStatus() = gameStatus
    fun getQuestionNumber() = questionNumber
    fun getConsolidatedPoints() = consolidatedPoints
    fun getTimeStart() = timeStart
    fun getTimeEnd() = timeEnd
    fun getUsedHelp() = usedHelp
    fun getNumberHelp() = _numberUsedHelp.value

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

    fun setUsedHelp(used: Boolean) {
        usedHelp = used
    }

    fun setInFragmentChallenges(isIn: Boolean) {
        _inFragmentChallenges.value = isIn
    }

    fun startNumberHelp() {
        _numberUsedHelp.value = 0
    }

    fun addNumberHelp() {
        _numberUsedHelp.value = _numberUsedHelp.value?.plus(1)
    }

    fun addBtnPressed(char: Char) {
        _btnPressed.value = char
    }

    fun changeStartingVisibilityLetters(list: MutableList<Char>) {
        _visibleLetters.value = list
    }

    fun createGame(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val game =
                    GameDirector(
                        QuestionHangmanGameBuilder(
                            challengesRepository,
                            gameRepository
                        )
                    ).construct()
                if (game.sortChallengesByDifficulty()) {
                    _gameLiveData.postValue(game)
                }
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

        summaryStats.add(((timeEnd - timeStart) / 1000).toInt())
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

    suspend fun registerChallenge(challengeId: Int, challengeType: String) {
        gameRepository.addChallengeToGame(
            gameLiveData.value!!.gameId,
            challengeId,
            challengeType
        )
    }

    suspend fun registerScore(score: Int) {
        gameRepository.updateScore(gameLiveData.value!!.gameId, score)
    }

    suspend fun registerWin() {
        statisticsRepository.registerWinStatistic(Application.getUser()!!.id)
    }

    suspend fun registerLose() {
        statisticsRepository.registerLoseStatistic(Application.getUser()!!.id)
    }

    suspend fun registerQuit() {
        statisticsRepository.registerQuitStatistic(Application.getUser()!!.id)
    }

    suspend fun registerQuestionCorrect() {
        statisticsRepository.registerQuestionCorrectStatistics(Application.getUser()!!.id)
    }

    suspend fun registerQuestionFailed() {
        statisticsRepository.registerQuestionFailedStatistics(Application.getUser()!!.id)
    }

    suspend fun registerHangmanCorrect() {
        statisticsRepository.registerHangmanCorrectStatistics(Application.getUser()!!.id)
    }

    suspend fun registerHangmanFailed() {
        statisticsRepository.registerHangmanFailedStatistics(Application.getUser()!!.id)
    }

    suspend fun registerTime(time: Int) {
        statisticsRepository.registerTimeStatistics(Application.getUser()!!.id, time)
    }

    //TIMER
    fun startCountDownTimer(duration: Long) {
        countDownTimer?.cancel()
        secondsLeft = duration

        countDownTimer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsLeft = millisUntilFinished
                countdownLiveData.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                secondsLeft = 0
                countdownLiveData.value = 0
            }
        }.start()
    }

    fun stopCountDownTimer() {
        countDownTimer?.cancel()
    }

    fun resumeCountDownTimer() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(secondsLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsLeft = millisUntilFinished
                countdownLiveData.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                secondsLeft = 0
                countdownLiveData.value = 0
            }
        }.start()
    }

}