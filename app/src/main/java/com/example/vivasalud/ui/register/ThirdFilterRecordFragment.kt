package com.example.vivasalud.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vivasalud.R
import com.example.vivasalud.data.local.database.AppDatabase
import com.example.vivasalud.data.repository.UserRepository
import com.example.vivasalud.data.viewModel.RegistroViewModel
import com.example.vivasalud.data.viewModel.RegistroViewModelFactory
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class ThirdFilterRecordFragment : Fragment() {

    private val registroViewModel: RegistroViewModel by activityViewModels {
        val dao = AppDatabase.getDatabase(requireContext()).userDao()
        val repository = UserRepository(dao)
        RegistroViewModelFactory(repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third_filter_record, container, false)
    }

    private lateinit var etClave: TextInputEditText
    private lateinit var etRepetirClave: TextInputEditText
    private lateinit var btnRegistrar: MaterialButton


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etClave = view.findViewById(R.id.spClave)
        etRepetirClave = view.findViewById(R.id.spRepeatClave)
        btnRegistrar = view.findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            val clave = etClave.text.toString().trim()
            val repetirClave = etRepetirClave.text.toString().trim()

            if (clave.isEmpty() || repetirClave.isEmpty()) {
                Toast.makeText(requireContext(), "Debe llenar ambos campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (clave != repetirClave) {
                Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registroViewModel.filtroRegistroTres(clave)

            registroViewModel.registrarUsuarioEnBD()

            val usuarioFinal = registroViewModel.usuario.value

            Log.d("REGISTRO_FINAL", usuarioFinal.toString())
            Toast.makeText(requireContext(), "Cuenta Creada con Éxito!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.logInFragment)

        }
    }

}