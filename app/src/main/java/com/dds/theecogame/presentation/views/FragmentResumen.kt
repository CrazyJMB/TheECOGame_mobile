package com.dds.theecogame.presentation.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dds.theecogame.databinding.FragmentResumenBinding
import com.dds.theecogame.presentation.viewmodel.GameViewModel
import com.dds.theecogame.presentation.views.MainScreenActivityView as MainScreenActivityView

class FragmentResumen : Fragment() {
    private lateinit var binding: FragmentResumenBinding

    //Codigo de inicializacion (como configurar una variable)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeSummary()
    }

    //Inflar el diseño del fragment y devolver la vista
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResumenBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Codigo para inicializar la vista (como podria ser un listener para un boton)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.acabarPartida.setOnClickListener {
            //Enviar los datos para que se guarden y tal...
            GameViewModel().sendResults()


            //Irse al menu principal
            val mainScreen = Intent(activity, MainScreenActivityView::class.java)
            startActivity(mainScreen)
            activity?.finish()
        }
    }

    private fun initializeSummary (){
        val statValues = GameViewModel().getResults()
        binding.timePlayed.text = "${binding.timePlayed.text} ${statValues[0]}"
        binding.pointsObtained.text = "${binding.pointsObtained.text} ${statValues[1]}"
        binding.questionsAnswered.text = "${binding.questionsAnswered.text} ${statValues[2]}/10"
    }
}