package com.dds.theecogame.presentation.game.view

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat
//import androidx.databinding.ObservableInt
import com.dds.theecogame.R


import com.dds.theecogame.databinding.FragmentQuestionsBinding
import com.dds.theecogame.presentation.game.viewModel.GameViewModel

class QuestionFragment : Fragment() {
    private var countDownTimer: CountDownTimer? = null
    private lateinit var binding: FragmentQuestionsBinding
    //private val selectedRadioButtonId = ObservableInt()
    private lateinit var mediaPlayer: MediaPlayer
    var segundaOportunidad = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer()
        changeViewNumberQuestion()
        changeViewImage()

        binding.btnContinue.setOnClickListener {
            val respuestaCorrecta = "" //Base de Datos
            val esCorrecta = verificarRespuesta(respuestaCorrecta)
            if (esCorrecta) {
                val sharedPref = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                //TODO mirar la dificultad del la pregunta y a partir de ahi añadir puntos
                val points = 10
                if (segundaOportunidad){
                    GameViewModel().addPoints(sharedPref, points/2)
                } else {
                    GameViewModel().addPoints(sharedPref, points)
                }
                GameViewModel().addNumberChallengesAnswered(sharedPref)
                stopTimer()
                goToCongratulations()
            } else if (!esCorrecta && !segundaOportunidad) {
                segundaOportunidad = true
            } else {
                goToSummary()
            }
        }
    }

    fun verificarRespuesta(respuestaCorrecta: String): Boolean {
        val respuestaSeleccionadaId = binding.radioGroup.checkedRadioButtonId

        if (respuestaSeleccionadaId == -1) {

            Toast.makeText(requireContext(), "Debes seleccionar una respuesta", Toast.LENGTH_SHORT)
                .show()
            return false
        } else {

            val respuestaSeleccionada =
                binding.radioGroup.findViewById<RadioButton>(respuestaSeleccionadaId).text.toString()

            if (respuestaSeleccionada == respuestaCorrecta) {

                onViewCreated(binding.root, null)
                segundaOportunidad = false
                // detenerTemporizador()
                return true

            } else {

                if (!segundaOportunidad) {

                    Toast.makeText(
                        requireContext(),
                        "Respuesta incorrecta. Tienes otra oportunidad.",
                        Toast.LENGTH_SHORT
                    ).show()
                    playLosingMusic(true)

                } else {

                    Toast.makeText(
                        requireContext(),
                        "Respuesta incorrecta. No tienes más oportunidades.",
                        Toast.LENGTH_SHORT
                    ).show()
                    playLosingMusic(false)
                    // detenerTemporizador()

                }
                return false
            }

        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(30000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                binding.TiempoRestante.text = (millisUntilFinished / 1000).toString()
                if ((millisUntilFinished / 1000).toInt() == 10) {
                    playTenseMusic()
                }
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                mediaPlayer.stop()
                playLosingMusic(false)
                goToSummary()
            }
        }.start()
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    private fun playLosingMusic(firstTime: Boolean) {
        if (firstTime) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.fallo)
        } else {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.perder)
        }
        mediaPlayer.isLooping = false
        mediaPlayer.start()
    }

    private fun playTenseMusic() {
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.tensa)
        mediaPlayer.isLooping = false
        mediaPlayer.start()
    }

    private fun goToCongratulations() {
        val congratulationFragment = CongratulationFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, congratulationFragment)
            .commit()
    }

    private fun goToSummary() {
        val summaryFragment = ResumeFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, summaryFragment)
            .commit()
    }

    private fun changeViewNumberQuestion(){
        val sharedPref = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val number = GameViewModel().getNumberQuestion(sharedPref)
        binding.NumeroPregunta.text = (number + 1).toString()
    }

    private fun changeViewImage(){
        binding.ImagenODS.setImageResource(R.drawable.ods1)
    }
}

