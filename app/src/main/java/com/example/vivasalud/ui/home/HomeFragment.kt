package com.example.vivasalud.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.vivasalud.R
import com.example.vivasalud.data.viewModel.RegistroViewModel
import com.example.vivasalud.ui.components.ItemCardView
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {

    private val registroViewModel: RegistroViewModel by activityViewModels()

    private lateinit var btnImagen: ImageButton
    private lateinit var btnAgendarCita: ItemCardView
    private lateinit var btnVerCitas: ItemCardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnImagen = view.findViewById(R.id.btnImagen)
        btnAgendarCita = view.findViewById(R.id.btnAgendarCita)
        btnVerCitas = view.findViewById(R.id.btnVerCitas)

        val headerTextView = view.findViewById<TextView>(R.id.headerTitle)
        registroViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                headerTextView.text = "¡Hola ${it.name}!"
            }
        }

        btnImagen.setOnClickListener {
            findNavController().navigate(R.id.infoUserFragment)
        }
        btnAgendarCita.setOnClickListener {
            findNavController().navigate(R.id.createCitaFragment)
        }
        btnVerCitas.setOnClickListener{
            findNavController().navigate(R.id.listCitaFragment)
        }

    }
}