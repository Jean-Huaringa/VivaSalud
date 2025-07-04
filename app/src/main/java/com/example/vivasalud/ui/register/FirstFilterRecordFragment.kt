package com.example.vivasalud.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vivasalud.R
import com.example.vivasalud.data.local.database.AppDatabase
import com.example.vivasalud.data.model.Usuario
import com.example.vivasalud.data.repository.UserRepository
import com.example.vivasalud.data.viewModel.RegistroViewModel
import com.example.vivasalud.data.viewModel.RegistroViewModelFactory
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText


class FirstFilterRecordFragment : Fragment() {

    private val registroViewModel: RegistroViewModel by activityViewModels  {
        val dao = AppDatabase.getDatabase(requireContext()).userDao()
        val repository = UserRepository(dao)
        RegistroViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_first_filter_record, container, false)
    }

    private lateinit var btnSiguiente: MaterialButton
    private lateinit var spTypeDocument: AutoCompleteTextView
    private lateinit var etDocument: TextInputEditText
    private lateinit var etBirthdate: TextInputEditText
    private lateinit var checkBox: MaterialCheckBox

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSiguiente = view.findViewById(R.id.btnSiguientePaso)
        spTypeDocument = view.findViewById(R.id.spTypeDocument)
        etDocument = view.findViewById(R.id.etDocument)
        etBirthdate = view.findViewById(R.id.etBirthdate)
        checkBox = view.findViewById(R.id.checkbox)

        spTypeDocument.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, listOf("DNI", "CE", "Pasaporte")))

        spTypeDocument.setOnClickListener {
            val im = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
            im?.hideSoftInputFromWindow(spTypeDocument.windowToken, 0)
        }

        checkBox.setOnCheckedChangeListener { _, _ ->
            validarCampos()
        }

        btnSiguiente.setOnClickListener {
            val typeDoc = spTypeDocument.text.toString()
            val document = etDocument.text.toString()
            val birthdate = etBirthdate.text.toString()
            val acceptedTerms = checkBox.isChecked

            if (acceptedTerms) {
                registroViewModel.filtroRegistroUno(typeDoc, document, birthdate)

                findNavController().navigate(R.id.secondFilterRecordFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Para continuar debe aceptar los términos y condiciones",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validarCampos() {
        val typeDocument = spTypeDocument.text?.toString()?.trim().orEmpty()
        val document = etDocument.text?.toString()?.trim().orEmpty()
        val birthdate = etBirthdate.text?.toString()?.trim().orEmpty()
        val acceptedTerms = checkBox.isChecked

        val documentoValido = document.length == 8 && document.all { it.isDigit() }


        btnSiguiente.isEnabled = typeDocument.isNotEmpty() && documentoValido && birthdate.isNotEmpty() && acceptedTerms
    }

}