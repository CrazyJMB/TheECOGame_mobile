package com.dds.theecogame.presentation.game.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ConsolidateFragment : Fragment() {

    private val _binding: ConsolidateFragment? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return view
    }

    fun goToConsolidateScreen(destinationFragment: Fragment, idButton: Int, idContainer: Int) {
        val fragmentButton = view?.findViewById<Button>(idButton)
        fragmentButton?.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(idContainer, destinationFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
    }

}