package com.dds.theecogame.presentation.game.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentResumenBinding
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity

class ResumeFragment : Fragment() {
    private lateinit var binding: FragmentResumenBinding

    //Inflar el diseño del fragment y devolver la vista
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResumenBinding.inflate(inflater)
        return binding.root
    }

    //Codigo para inicializar la vista (como podria ser un listener para un boton)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeSummary()

        binding.acabarPartida.setOnClickListener {
            //Enviar los datos para que se guarden y tal...
            GameViewModel().sendResults()

            //Irse al menu principal
            val mainScreen = Intent(activity, MainScreenActivity::class.java)
            startActivity(mainScreen)
        }
    }

    private fun initializeSummary() {
        val sharedPref = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        GameViewModel().setTimeEnded(sharedPref)
        val statValues = GameViewModel().getResults(sharedPref)

        val minutes = statValues[0]/60
        val seconds = statValues[0]%60

        binding.timePlayed.text = "${binding.timePlayed.text} ${minutes}min ${seconds}sec"
        binding.pointsObtained.text = "${binding.pointsObtained.text} ${statValues[1]}"
        binding.questionsAnswered.text = "${binding.questionsAnswered.text} ${statValues[2]}/10"

        if (GameViewModel().hasUserAbandoned(sharedPref)) {
            binding.Title.setText(R.string.abandoned)
        } else {
            if (GameViewModel().hasUserWon(sharedPref)) {
                binding.Title.setText(R.string.victory)
            } else {
                binding.Title.setText(R.string.lost)
            }
        }
    }
}