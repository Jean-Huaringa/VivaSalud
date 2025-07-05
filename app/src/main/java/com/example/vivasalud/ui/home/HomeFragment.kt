package com.example.vivasalud.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.vivasalud.R
import com.example.vivasalud.ui.components.ItemCardView
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {


    private lateinit var btnAgendarCita: ItemCardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAgendarCita = view.findViewById(R.id.btnAgendarCita)
        btnAgendarCita.setOnClickListener {
            findNavController().navigate(R.id.createCitaFragment)
        }

    }
}