package com.example.vivasalud.ui.cita

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.vivasalud.R
import com.example.vivasalud.data.local.database.AppDatabase
import com.example.vivasalud.data.repository.CitaRepository
import com.example.vivasalud.data.viewModel.cita.CitaViewModel
import com.example.vivasalud.data.viewModel.cita.CitaViewModelFactory
import com.google.android.material.button.MaterialButton

class SeleccionLugarFragment : Fragment() {


    private val citaViewModel: CitaViewModel by activityViewModels {
        val dao = AppDatabase.getDatabase(requireContext()).citaDao()
        val repository = CitaRepository(dao)
        CitaViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seleccion_lugar, container, false)
    }


    private lateinit var btnCardOne: CardView
    private lateinit var btnCardTwo: CardView
    private lateinit var txtLugar: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCardOne = view.findViewById(R.id.cardOne)
        btnCardTwo = view.findViewById(R.id.cardTwo)

        btnCardOne.setOnClickListener {
            txtLugar = view.findViewById(R.id.hospitalOne)
            val texto = txtLugar.text.toString()
            citaViewModel.seletLugarCita(texto)
            findNavController().navigate(R.id.seleccionDoctorFragment)
        }

        btnCardTwo.setOnClickListener {
            txtLugar = view.findViewById(R.id.hospitalTwo)
            val texto = txtLugar.text.toString()
            citaViewModel.seletLugarCita(texto)
            findNavController().navigate(R.id.seleccionDoctorFragment)
        }
    }

}