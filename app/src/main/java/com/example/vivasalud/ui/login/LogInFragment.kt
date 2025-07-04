package com.example.vivasalud.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.vivasalud.R
import com.example.vivasalud.data.local.database.AppDatabase
import com.example.vivasalud.data.repository.UserRepository
import com.example.vivasalud.data.viewModel.RegistroViewModel
import com.example.vivasalud.data.viewModel.RegistroViewModelFactory
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LogInFragment : Fragment() {

    private val registroViewModel: RegistroViewModel by activityViewModels  {
        val dao = AppDatabase.getDatabase(requireContext()).userDao()
        val repository = UserRepository(dao)
        RegistroViewModelFactory(repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }
    private lateinit var btnEnviar: MaterialButton
    private lateinit var btnCrearCuenta: TextView
    private lateinit var spTypeDocument: AutoCompleteTextView
    private lateinit var etPassword: TextInputEditText
    private lateinit var etDocument: TextInputEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEnviar = view.findViewById(R.id.btnEnviar)
        btnCrearCuenta = view.findViewById(R.id.btnCreateAccount)
        spTypeDocument = view.findViewById(R.id.spTypeDocuments)
        etPassword = view.findViewById(R.id.etPassword)
        etDocument = view.findViewById(R.id.etDocument)

        val typeDocument = listOf("DNI", "CE", "Pasaporte")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, typeDocument)
        spTypeDocument.setAdapter(adapter)

        spTypeDocument.setOnClickListener {
            val im = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
            im?.hideSoftInputFromWindow(spTypeDocument.windowToken, 0)
        }

        btnEnviar.setOnClickListener {

            val typeDocument = spTypeDocument.text.toString()
            val document = etDocument.text.toString()
            val password = etPassword.text.toString()

            if (typeDocument.isEmpty() ||document.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registroViewModel.login(typeDocument, document, password) { usuario ->
                if (usuario != null) {
                    Toast.makeText(requireContext(), "Bienvenido, ${usuario.name}", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.homeFragment)
                } else {
                    Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnCrearCuenta?.setOnClickListener {
            findNavController().navigate(R.id.firstFilterRecordFragment)
        }
    }
}