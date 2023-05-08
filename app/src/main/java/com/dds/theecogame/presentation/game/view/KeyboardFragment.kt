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
        // Inflate the layout for this fragment
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

        binding.root.setOnClickListener{
            val btnID = view.id
            val btnStr = resources.getResourceEntryName(btnID)
            val btnChar = btnStr.getOrNull(btnStr.length - 1)

            if (btnChar in 'A'..'Z'){
                view.visibility = View.INVISIBLE
                gameViewModel.addBtnPressed(btnChar!!.toChar())
            }
        }

    }

}