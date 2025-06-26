package com.example.vivasalud

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class SecondFilterRecord : Fragment() {

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
    private lateinit var spPais: AutoCompleteTextView
    private lateinit var spDepartamento: AutoCompleteTextView
    private lateinit var spProvincia: AutoCompleteTextView
    private lateinit var spDistrito: AutoCompleteTextView
    private lateinit var etDomicilio: TextInputEditText
    private lateinit var btnSiguiente: MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usuario = arguments?.getParcelable<Usuario>("usuario")

        usuario?.let {
            Log.d("USUARIO", "Tipo doc: ${it.typeDocument}, Doc: ${it.numberDocument}, Fecha: ${it.birthdate}")

        }

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
            val name = etName.text.toString()
            val paterno = etPaternalSurname.text.toString()
            val materno = etMaternalSurname.text.toString()
            val sexo = etSexo.text.toString()
            val seguro = spSeguro.text.toString()
            val pais = spPais.text.toString()
            val departamento = spDepartamento.text.toString()
            val provincia = spProvincia.text.toString()
            val distrito = spDistrito.text.toString()
            val domicilio = etDomicilio.text.toString()
        }


        val sexo = listOf("Masculino", "Femenino", "Nose")
        val adapterSexo = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, sexo)
        etSexo.setAdapter(adapterSexo)

        val seguros = listOf("Seguro 1", "Seguro 2", "Seguro 3")
        val adapterSeguros = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, seguros)
        spSeguro.setAdapter(adapterSeguros)

        val paises = listOf("Perú", "Argentina", "Chile")
        val adapterPaises = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, paises)
        spPais.setAdapter(adapterPaises)

        val departamentos = listOf("Lima", "Arequipa", "Cusco")
        val adapterDepartamentos = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, departamentos)
        spDepartamento.setAdapter(adapterDepartamentos)

        val provincias = listOf("Lima", "Trujillo", "Arequipa")
        val adapterProvincias = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, provincias)
        spProvincia.setAdapter(adapterProvincias)

        val distritos = listOf("Miraflores", "San Isidro", "Chorrillos", "San Juan de Lurigancho", "Los Olivos")
        val adapterDistritos = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, distritos)
        spDistrito.setAdapter(adapterDistritos)

        /*
        etSexo.setOnItemClickListener {parent, view, position, id ->
            val seleccionado = parent.getItemAtPosition(position).toString()
            etSexo.showDropDown()
        }
        */

        etSexo.setOnClickListener {
            val im = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
            im?.hideSoftInputFromWindow(etSexo.windowToken, 0)
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

}