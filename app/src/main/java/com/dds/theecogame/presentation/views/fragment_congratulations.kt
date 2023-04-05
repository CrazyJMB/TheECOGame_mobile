package com.dds.theecogame.presentation.views

import android.content.Intent
import android.media.AsyncPlayer
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dds.theecogame.R
import com.dds.theecogame.R.raw
import com.dds.theecogame.databinding.FragmentCongratulationsBinding
import com.dds.theecogame.databinding.FragmentResumenBinding
import com.dds.theecogame.presentation.viewmodel.GameViewModel

class fragment_congratulations : Fragment() {
    private lateinit var binding: FragmentCongratulationsBinding
    private lateinit var mediaPlayer: MediaPlayer

    //Codigo de inicializacion (como configurar una variable)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startMusic()
    }

    //Inflar el diseño del fragment y devolver la vista
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCongratulationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Codigo para inicializar la vista (como podria ser un listener para un boton)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.title.setOnClickListener{
            mediaPlayer.stop()
            if (GameViewModel().askIfUserHasntFinished()){
                goToConsolidate()
            } else {
                goToSummary()
            }
        }
        mediaPlayer.setOnCompletionListener {
            if (GameViewModel().askIfUserHasntFinished()){
                goToConsolidate()
            } else {
                goToSummary()
            }
        }
    }

    private fun startMusic(){
        if (GameViewModel().askIfUserHasntFinished()){
            mediaPlayer = MediaPlayer.create(requireContext(), raw.ganar_reto)
        } else {
            mediaPlayer = MediaPlayer.create(requireContext(), raw.victoria)
        }
        mediaPlayer.isLooping = false
        mediaPlayer.start()
    }

    private fun goToConsolidate(){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val newFragment = fragment_consolidate()
        fragmentTransaction.replace(R.id.fragmentContainerView, newFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun goToSummary(){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val newFragment = FragmentResumen()
        fragmentTransaction.replace(R.id.fragmentContainerView, newFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}