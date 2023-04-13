package com.dds.theecogame.presentation.game.view

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dds.theecogame.R
import com.dds.theecogame.R.raw
import com.dds.theecogame.databinding.FragmentCongratulationsBinding
import com.dds.theecogame.presentation.game.view.ConsolidateFragment
import com.dds.theecogame.presentation.game.view.ResumeFragment
import com.dds.theecogame.presentation.game.viewModel.GameViewModel

class CongratulationFragment : Fragment() {
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
        binding.title.setOnClickListener {
            mediaPlayer.stop()
            if (GameViewModel().hasUserAnsweredAll()) {
                goToSummary()
            } else {
                goToConsolidate()
            }
        }
        mediaPlayer.setOnCompletionListener {
            if (GameViewModel().hasUserAnsweredAll()) {
                goToSummary()
            } else {
                goToConsolidate()
            }
        }
    }

    private fun startMusic() {
        if (GameViewModel().hasUserAnsweredAll()) {
            mediaPlayer = MediaPlayer.create(requireContext(), raw.ganar_reto)
        } else {
            mediaPlayer = MediaPlayer.create(requireContext(), raw.victoria)
        }
        mediaPlayer.isLooping = false
        mediaPlayer.start()
    }

    private fun goToConsolidate() {
        val consolidateFragment = ConsolidateFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, consolidateFragment)
            .commit()
    }

    private fun goToSummary() {
        val summaryFragment = ResumeFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.GameContainerView, summaryFragment)
            .commit()
    }
}