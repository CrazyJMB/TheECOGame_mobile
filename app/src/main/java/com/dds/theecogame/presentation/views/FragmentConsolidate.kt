package com.dds.theecogame.presentation.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivityEstadisticaBinding

class FragmentConsolidate : Fragment() {

    private val _binding: FragmentConsolidate? = null

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