package com.dds.theecogame.presentation.game.challenges.hangman.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentKeyboardBinding
import com.dds.theecogame.presentation.game.viewModel.GameViewModel

class KeyboardFragment : Fragment() {
    private lateinit var binding: FragmentKeyboardBinding
    private val gameViewModel: GameViewModel by activityViewModels()

    private val buttonIds = listOf(
        R.id.btnA,
        R.id.btnB,
        R.id.btnC,
        R.id.btnD,
        R.id.btnE,
        R.id.btnF,
        R.id.btnG,
        R.id.btnH,
        R.id.btnI,
        R.id.btnJ,
        R.id.btnK,
        R.id.btnL,
        R.id.btnM,
        R.id.btnN,
        R.id.btnO,
        R.id.btnP,
        R.id.btnQ,
        R.id.btnR,
        R.id.btnS,
        R.id.btnT,
        R.id.btnU,
        R.id.btnV,
        R.id.btnW,
        R.id.btnX,
        R.id.btnY,
        R.id.btnZ
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKeyboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameViewModel.visibleLetters.observe(viewLifecycleOwner) { visibleLetters ->
            for (letter in visibleLetters) {
                if (letter != ' ') {
                    val btnID =
                        resources.getIdentifier("btn$letter", "id", requireContext().packageName)
                    val button = binding.root.findViewById<Button>(btnID)
                    button.visibility = View.INVISIBLE
                }
            }
        }

        for (buttonId in buttonIds) {
            binding.root.findViewById<Button>(buttonId).setOnClickListener {
                val button = it as Button
                button.visibility = View.INVISIBLE
                val char = button.text.last()
                gameViewModel.addBtnPressed(char)
            }
        }
    }
}