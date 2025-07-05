package com.example.vivasalud.ui.cita

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.vivasalud.R
import com.example.vivasalud.VivaSaludApplication
import com.example.vivasalud.data.local.database.AppDatabase
import com.example.vivasalud.data.model.Cita
import com.example.vivasalud.data.repository.CitaRepository
import com.example.vivasalud.data.viewModel.cita.CitaViewModel
import com.example.vivasalud.data.viewModel.cita.CitaViewModelFactory

class ListCitaFragment : Fragment() {


    private val citaViewModel: CitaViewModel by activityViewModels {
        val dao = AppDatabase.getDatabase(requireContext()).citaDao()
        val repository = CitaRepository(dao)
        CitaViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seleccion_doctor, container, false)
    }

}