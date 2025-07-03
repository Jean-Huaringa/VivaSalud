package com.example.vivasalud.ui.cita

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.vivasalud.VivaSaludApplication
import com.example.vivasalud.data.local.database.AppDatabase
import com.example.vivasalud.data.model.Cita
import com.example.vivasalud.databinding.FragmentListCitaBinding
import com.example.vivasalud.ui.adapter.CitaAdapter
import java.util.concurrent.LinkedBlockingQueue

class ListCitaFragment : Fragment(), OnClickListener {

    private lateinit var citaAdapter: CitaAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var binding: FragmentListCitaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCitaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getCitas()
    }

    private fun setupRecyclerView() {
        citaAdapter = CitaAdapter(mutableListOf(), this)
        gridLayoutManager = GridLayoutManager(context, 2)

        binding.recyclerViewCitas.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            adapter = citaAdapter
        }
    }

    private fun getCitas() {
        val queue = LinkedBlockingQueue<MutableList<Cita>>()

        // Usamos un hilo para obtener las citas desde la base de datos
        Thread {
            val citas = VivaSaludApplication.database.citaDao().getAllCitas()
            queue.add(citas)
        }.start()

        citaAdapter.setCitas(queue.take())
    }

    override fun onDeleteStore(cita: Cita) {
        // Manejar eliminación de la cita
        Thread {
            VivaSaludApplication.database.citaDao().insert(cita)  // Aquí puedes agregar otras operaciones
        }.start()

        // Recargar las citas
        getCitas()
    }

    override fun onClick(cita: Cita) {
        TODO("Not yet implemented")
    }
}