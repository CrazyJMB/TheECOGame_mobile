package com.dds.theecogame.presentation.game.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dds.theecogame.R
import com.dds.theecogame.databinding.FragmentKeyboardBinding

class KeyboardFragment : Fragment() {
    private lateinit var binding: FragmentKeyboardBinding

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

        binding.root.setOnClickListener{

            val btnID = view.id
            val btnChar = resources.getResourceEntryName(btnID).lastOrNull()
            if (btnChar in 'A'..'Z'){
                view.visibility = View.INVISIBLE

            }
        }

    }

}