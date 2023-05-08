package com.dds.theecogame.presentation.game.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentKeyboardBinding
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.game.viewModel.QuestionViewModel

class KeyboardFragment : Fragment() {
    private lateinit var binding: FragmentKeyboardBinding
    private val gameViewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKeyboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameViewModel.visibleLetters.observe(viewLifecycleOwner){
            for (letter in it){
                val button = binding.root.findViewWithTag<Button>("btn$letter")
                button.visibility = View.INVISIBLE
            }
        }

        binding.btnA.setOnClickListener {
            val aux = binding.btnA
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnB.setOnClickListener {
            val aux = binding.btnB
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnC.setOnClickListener {
            val aux = binding.btnC
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnD.setOnClickListener {
            val aux = binding.btnD
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnE.setOnClickListener {
            val aux = binding.btnE
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnF.setOnClickListener {
            val aux = binding.btnF
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnG.setOnClickListener {
            val aux = binding.btnG
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnH.setOnClickListener {
            val aux = binding.btnH
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnI.setOnClickListener {
            val aux = binding.btnI
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnJ.setOnClickListener {
            val aux = binding.btnJ
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnK.setOnClickListener {
            val aux = binding.btnK
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnL.setOnClickListener {
            val aux = binding.btnL
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnM.setOnClickListener {
            val aux = binding.btnM
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnN.setOnClickListener {
            val aux = binding.btnN
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnO.setOnClickListener {
            val aux = binding.btnO
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnP.setOnClickListener {
            val aux = binding.btnP
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnQ.setOnClickListener {
            val aux = binding.btnQ
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnR.setOnClickListener {
            val aux = binding.btnR
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnS.setOnClickListener {
            val aux = binding.btnS
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnT.setOnClickListener {
            val aux = binding.btnT
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnU.setOnClickListener {
            val aux = binding.btnU
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnV.setOnClickListener {
            val aux = binding.btnV
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnW.setOnClickListener {
            val aux = binding.btnW
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnX.setOnClickListener {
            val aux = binding.btnX
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnY.setOnClickListener {
            val aux = binding.btnY
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }

        binding.btnZ.setOnClickListener {
            val aux = binding.btnZ
            aux.visibility = View.INVISIBLE
            val char = aux.text.last()
            gameViewModel.addBtnPressed(char)
        }
    }
}