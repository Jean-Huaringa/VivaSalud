package com.example.vivasalud.ui.cita

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vivasalud.R
import com.example.vivasalud.data.local.database.AppDatabase
import com.example.vivasalud.data.model.Medico
import com.example.vivasalud.data.repository.CitaRepository
import com.example.vivasalud.data.viewModel.cita.CitaViewModel
import com.example.vivasalud.data.viewModel.cita.CitaViewModelFactory
import com.example.vivasalud.ui.medico.MedicoAdapter
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class SeleccionDoctorFragment : Fragment() {

    private val TAG = "Firestore"
    private lateinit var medicoAdapter: MedicoAdapter

    private val citaViewModel: CitaViewModel by activityViewModels {
        val dao = AppDatabase.getDatabase(requireContext()).citaDao()
        val repository = CitaRepository(dao)
        CitaViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar Firebase
        FirebaseApp.initializeApp(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seleccion_doctor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Instancia de Firestore
        val db = FirebaseFirestore.getInstance()

        // RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.medicosContainer)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Leer colección "medicos"
        db.collection("medicos")
            .get()
            .addOnSuccessListener { result ->
                val medicosList = mutableListOf<Medico>()
                for (document in result) {
                    val medico = document.toObject(Medico::class.java).copy(id = document.id)
                    medicosList.add(medico)
                }

                // Configurar el adaptador del RecyclerView
                medicoAdapter = MedicoAdapter(medicosList) { medico ->
                    // Aquí puedes manejar el clic en el médico

                    Toast.makeText(requireContext(), " ${medico.id}, ${medico.nombre}", Toast.LENGTH_SHORT).show()

                }

                recyclerView.adapter = medicoAdapter
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error al obtener médicos", e)
            }
    }

}