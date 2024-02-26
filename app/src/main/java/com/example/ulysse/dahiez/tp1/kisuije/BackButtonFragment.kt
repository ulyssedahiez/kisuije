package com.example.ulysse.dahiez.tp1.kisuije

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class BackButtonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_back_button, container, false)
        val backButton = view.findViewById<Button>(R.id.backButtonFragment)

        // Ajouter un écouteur de clic au bouton
        backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }
}
