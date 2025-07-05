package com.example.vivasalud.ui.cita

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.vivasalud.R
import com.example.vivasalud.VivaSaludApplication
import com.example.vivasalud.data.local.database.AppDatabase
import com.example.vivasalud.data.model.Cita
import com.example.vivasalud.data.repository.CitaRepository
import com.example.vivasalud.data.viewModel.RegistroViewModel
import com.example.vivasalud.data.viewModel.cita.CitaViewModel
import com.example.vivasalud.data.viewModel.cita.CitaViewModelFactory

class ListCitaFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val registroViewModel: RegistroViewModel by activityViewModels()

    private lateinit var citaViewModel: CitaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_cita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recyclerViewCitas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val application = requireActivity().application
        val database = AppDatabase.getDatabase(application)
        val repository = CitaRepository(database.citaDao())

        val factory = CitaViewModelFactory(repository)
        citaViewModel = ViewModelProvider(this, factory)[CitaViewModel::class.java]

        registroViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                val nombrePaciente = "${it.name} ${it.paternalSurname} ${it.maternalSurname}"
                citaViewModel.getCitaByIdUser(it.id)

                citaViewModel.citas.observe(viewLifecycleOwner) { listaCitas ->
                    recyclerView.adapter = CitaAdapter(listaCitas, nombrePaciente) { citaAEliminar ->
                        citaViewModel.deleteCita(citaAEliminar)
                    }
                }
            }
        }
    }
}