package com.example.vivasalud.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
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


class SecondFilterRecordFragment : Fragment() {

    private val registroViewModel: RegistroViewModel by activityViewModels {
        val dao = AppDatabase.getDatabase(requireContext()).userDao()
        val repository = UserRepository(dao)
        RegistroViewModelFactory(repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_filter_record, container, false)
    }

    private lateinit var etName: TextInputEditText
    private lateinit var etPaternalSurname: TextInputEditText
    private lateinit var etMaternalSurname: TextInputEditText
    private lateinit var etSexo: AutoCompleteTextView
    private lateinit var spSeguro: AutoCompleteTextView
    private lateinit var spPais: TextInputEditText
    private lateinit var spDepartamento: TextInputEditText
    private lateinit var spProvincia: TextInputEditText
    private lateinit var spDistrito: TextInputEditText
    private lateinit var etDomicilio: TextInputEditText
    private lateinit var btnSiguiente: MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        etName = view.findViewById(R.id.etName)
        etPaternalSurname = view.findViewById(R.id.etPaternalSurname)
        etMaternalSurname = view.findViewById(R.id.etMaternalSurname)
        etSexo = view.findViewById(R.id.etSexo)
        spSeguro = view.findViewById(R.id.spSeguro)
        spPais = view.findViewById(R.id.spPais)
        spDepartamento = view.findViewById(R.id.spDepartamento)
        spProvincia = view.findViewById(R.id.spProvincia)
        spDistrito = view.findViewById(R.id.spDistrito)
        etDomicilio = view.findViewById(R.id.etDomicilio)
        btnSiguiente = view.findViewById(R.id.btnSiguiente)

        btnSiguiente.setOnClickListener {
            registroViewModel.filtroRegistroDos(
                name = etName.text.toString(),
                apePat = etPaternalSurname.text.toString(),
                apeMat = etMaternalSurname.text.toString(),
                sexo = etSexo.text.toString(),
                seguro = spSeguro.text.toString(),
                pais = spPais.text.toString(),
                departamento = spDepartamento.text.toString(),
                provincia = spProvincia.text.toString(),
                distrito = spDistrito.text.toString(),
                home = etDomicilio.text.toString()
            )

            findNavController().navigate(R.id.thirdFilterRecordFragment)
        }
        etSexo.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, listOf("Masculino", "Femenino")))
        spSeguro.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, listOf("SIS", "RIMAC", "MAPFPRE")))

        etSexo.setOnClickListener {
            ocultarTeclado(etSexo)
        }
        spSeguro.setOnClickListener {
            ocultarTeclado(spSeguro)

        }

        etName.addTextChangedListener{ validarCampos() }
        etPaternalSurname.addTextChangedListener{ validarCampos() }
        etMaternalSurname.addTextChangedListener{ validarCampos() }
        etSexo.addTextChangedListener{ validarCampos() }
        spSeguro.addTextChangedListener{ validarCampos() }
        spPais.addTextChangedListener{ validarCampos() }
        spDepartamento.addTextChangedListener{ validarCampos() }
        spProvincia.addTextChangedListener{ validarCampos() }
        spDistrito.addTextChangedListener{ validarCampos() }
        etDomicilio.addTextChangedListener{ validarCampos() }
    }

    private fun validarCampos() {
        val name = etName.text?.toString()?.trim().orEmpty()
        val paternalSurname = etPaternalSurname.text?.toString()?.trim().orEmpty()
        val maternalSurname = etMaternalSurname.text?.toString()?.trim().orEmpty()
        val sexo = etSexo.text?.toString()?.trim().orEmpty()
        val seguro = spSeguro.text?.toString()?.trim().orEmpty()
        val pais = spPais.text?.toString()?.trim().orEmpty()
        val departamento = spDepartamento.text?.toString()?.trim().orEmpty()
        val provincia = spProvincia.text?.toString()?.trim().orEmpty()
        val distrito = spDistrito.text?.toString()?.trim().orEmpty()
        val domicilio = etDomicilio.text?.toString()?.trim().orEmpty()

        btnSiguiente.isEnabled = name.isNotEmpty() &&
                paternalSurname.isNotEmpty() &&
                maternalSurname.isNotEmpty() &&
                sexo.isNotEmpty() &&
                seguro.isNotEmpty() &&
                pais.isNotEmpty() &&
                departamento.isNotEmpty() &&
                provincia.isNotEmpty() &&
                distrito.isNotEmpty() &&
                domicilio.isNotEmpty()
    }

    private fun ocultarTeclado(view: View) {
        val im = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
        im?.hideSoftInputFromWindow(view.windowToken, 0)
    }

}