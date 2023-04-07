package com.dds.theecogame.presentation.views

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.dds.theecogame.R
import android.widget.Toast
import androidx.databinding.ObservableInt
import androidx.fragment.app.FragmentTransaction


import com.dds.theecogame.databinding.ActivityEstadisticaBinding
import com.dds.theecogame.databinding.FragmentQuestionsBinding

class FragmentQuestions : Fragment() {

    private lateinit var binding: FragmentQuestionsBinding
    private val selectedRadioButtonId = ObservableInt()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        comenzarTemporizador()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val Continuar = binding.Continuar

        binding.Continuar.setOnClickListener {
            val respuestaCorrecta = "" //Base de Datos
            val esCorrecta = verificarRespuesta(respuestaCorrecta)

            if (esCorrecta) {
                // Si la respuesta es correcta, muestra un mensaje de éxito o pasa al siguiente fragmento
                Toast.makeText(requireContext(), "Respuesta correcta", Toast.LENGTH_SHORT).show()
                // Pasa al siguiente fragmento o realiza la acción correspondiente
            } else {
                // Si la respuesta es incorrecta, muestra un mensaje de error o da otra oportunidad al usuario para seleccionar la respuesta correcta
                Toast.makeText(requireContext(), "Respuesta incorrecta", Toast.LENGTH_SHORT).show()
            }
        }


        /*binding.Continuar.setOnClickListener {

            val fragmentManager = requireActivity().supportFragmentManager

            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.Continuar, FragmentConsolidate())

            fragmentTransaction.commit()
        }*/


    }

    var segundaOportunidad = false
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
                detenerTemporizador()
                return true

            } else {

                if (!segundaOportunidad) {

                    Toast.makeText(
                        requireContext(),
                        "Respuesta incorrecta. Tienes otra oportunidad.",
                        Toast.LENGTH_SHORT
                    ).show()
                    segundaOportunidad = true

                } else {

                    Toast.makeText(
                        requireContext(),
                        "Respuesta incorrecta. No tienes más oportunidades.",
                        Toast.LENGTH_SHORT
                    ).show()
                    detenerTemporizador()

                }
                return false
            }

        }
    }

    var timer: CountDownTimer? = null
    fun comenzarTemporizador() {
        timer = object : CountDownTimer(30000, 1000) {
            var auxiliarTiempoRestante: String = binding.TiempoRestante.text.toString()
            override fun onTick(millisUntilFinished: Long) {
                binding.TiempoRestante.text = auxiliarTiempoRestante + " " + millisUntilFinished
            }

            override fun onFinish() {

                Toast.makeText(requireContext(), "Se ha agotado el tiempo", Toast.LENGTH_SHORT)
                    .show()
                // Realizar acción correspondiente al tiempo agotado (por ejemplo, volver al fragmento anterior)
            }
        }
        timer?.start()
    }

    fun detenerTemporizador() {
        timer?.cancel()
    }

}

