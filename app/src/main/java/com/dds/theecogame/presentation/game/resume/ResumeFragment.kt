package com.dds.theecogame.presentation.game.resume

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentResumenBinding
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResumeFragment : Fragment() {

    private lateinit var binding: FragmentResumenBinding

    private val gameViewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResumenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (gameViewModel.getQuestionNumber() > 10) {
            gameViewModel.setGameStatus(2)
        }

        initializeSummary()
        setTitle()

        gameViewModel.setGameEnded()
        gameViewModel.pauseMusic()

        binding.btnEndGame.setOnClickListener {
            //Irse al menu principal
            val mainScreen = Intent(activity, MainScreenActivity::class.java)
            startActivity(mainScreen)
        }
    }

    private fun initializeSummary() {
        gameViewModel.setTimeEnd()
        val statValues = gameViewModel.getResults()
        val minutes = statValues[0] / 60
        val seconds = statValues[0] % 60

        lifecycleScope.launch(Dispatchers.IO) {
            gameViewModel.registerTime(statValues[0])
            gameViewModel.registerScore(statValues[1])
        }

        binding.tvTimePlayed.text = "${binding.tvTimePlayed.text} ${minutes}min ${seconds}sec"
        binding.tvPointsObtained.text = "${binding.tvPointsObtained.text} ${statValues[1]}"
        binding.tvQuestionsAnswered.text =
            "${binding.tvQuestionsAnswered.text} ${statValues[2] - 1}/10"
    }

    private fun setTitle() {
        if (gameViewModel.getGameStatus() == 1) {
            binding.tvTitle.setText(R.string.abandoned)
            lifecycleScope.launch(Dispatchers.IO) { gameViewModel.registerQuit() }
        } else {
            if (gameViewModel.getGameStatus() == 2) {
                binding.tvTitle.setText(R.string.victory)
                lifecycleScope.launch(Dispatchers.IO) { gameViewModel.registerWin() }
            } else {
                binding.tvTitle.setText(R.string.lost)
                lifecycleScope.launch(Dispatchers.IO) { gameViewModel.registerLose() }
            }
        }
    }
}