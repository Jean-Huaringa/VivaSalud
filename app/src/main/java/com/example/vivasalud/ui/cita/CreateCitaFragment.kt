package com.example.vivasalud.ui.cita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.vivasalud.R
import com.example.vivasalud.data.local.database.AppDatabase
import com.example.vivasalud.data.repository.CitaRepository
import com.example.vivasalud.data.viewModel.cita.CitaViewModel
import com.example.vivasalud.data.viewModel.cita.CitaViewModelFactory
import com.google.android.material.button.MaterialButton

class CreateCitaFragment : Fragment() {


    private val citaViewModel: CitaViewModel by activityViewModels {
        val dao = AppDatabase.getDatabase(requireContext()).citaDao()
        val repository = CitaRepository(dao)
        CitaViewModelFactory(repository)
    }

    private lateinit var btnDomicilio: MaterialButton
    private lateinit var btnPresencial: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_cita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDomicilio = view.findViewById(R.id.btnDomicilio)
        btnPresencial = view.findViewById(R.id.btnPresencial)

        btnDomicilio.setOnClickListener {
            citaViewModel.seletTypeCita("Domicilio")
            findNavController().navigate(R.id.seleccionDoctorFragment)
        }

        btnPresencial.setOnClickListener {
            citaViewModel.seletTypeCita("Presencial")
            findNavController().navigate(R.id.seleccionLugarFragment)
        }
    }
}