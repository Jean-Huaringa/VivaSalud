package com.example.vivasalud.ui.cita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vivasalud.R
import com.example.vivasalud.VivaSaludApplication
import com.example.vivasalud.data.local.database.AppDatabase
import com.example.vivasalud.data.model.Cita
import com.example.vivasalud.data.model.User
import com.example.vivasalud.data.repository.CitaRepository
import com.example.vivasalud.data.viewModel.cita.CitaViewModel
import com.example.vivasalud.data.viewModel.cita.CitaViewModelFactory
import com.example.vivasalud.databinding.FragmentConfirmacionCitaBinding
import com.example.vivasalud.databinding.FragmentListCitaBinding
import com.google.android.material.button.MaterialButton
import java.util.concurrent.LinkedBlockingQueue

class ConfirmacionCitaFragment : Fragment() {

    private val citaViewModel: CitaViewModel by activityViewModels {
        val dao = AppDatabase.getDatabase(requireContext()).citaDao()
        val repository = CitaRepository(dao)
        CitaViewModelFactory(repository)
    }


    private lateinit var areaTextView: TextView
    private lateinit var nameDoctorTextView: TextView
    private lateinit var ubicationTextView: TextView
    private lateinit var namePacienteTextView: TextView
    private lateinit var btnCrearCita:MaterialButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmacion_cita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        areaTextView = view.findViewById(R.id.area)
        nameDoctorTextView = view.findViewById(R.id.name_doctor)
        ubicationTextView = view.findViewById(R.id.ubication)
        namePacienteTextView = view.findViewById(R.id.name_paciente)
        btnCrearCita = view.findViewById(R.id.btnCrearCita)

        citaViewModel.cita.observe(viewLifecycleOwner) { cita ->
            areaTextView.text = cita.area
            nameDoctorTextView.text = "${cita.doctor} (${cita.type})"
            ubicationTextView.text = cita.clinica
            namePacienteTextView.text = "Carlos Alberto Ruiz" // Ajusta esto si es necesario
        }

        btnCrearCita.setOnClickListener {
            citaViewModel.insertCita()

            Toast.makeText(context, "Cita confirmada", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.listCitaFragment)
        }

    }

}