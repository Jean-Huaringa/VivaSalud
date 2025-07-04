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
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {

    private val registroViewModel: RegistroViewModel by activityViewModels()

    private lateinit var btnImagen: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btnImagen = view.findViewById(R.id.btnImagen)

        val headerTextView = view.findViewById<TextView>(R.id.headerTitle)

        registroViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                headerTextView.text = "¡Hola ${it.name}!"
            }
        }

        btnImagen.setOnClickListener{
            findNavController().navigate(R.id.infoUserFragment)

        }

    }
}