package com.example.vivasalud

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class SecondFilterRecord : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_filter_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSiguiente = view.findViewById<MaterialButton>(R.id.btnSiguiente)

        val etName = view.findViewById<TextInputEditText>(R.id.etName)
        val etPaternalSurname = view.findViewById<TextInputEditText>(R.id.etPaternalSurname)
        val etMaternalSurname = view.findViewById<TextInputEditText>(R.id.etMaternalSurname)
        val etSexo = view.findViewById<AutoCompleteTextView>(R.id.etSexo)
        val spSeguro = view.findViewById<AutoCompleteTextView>(R.id.spSeguro)
        val spPais = view.findViewById<AutoCompleteTextView>(R.id.spPais)
        val spDepartamento = view.findViewById<AutoCompleteTextView>(R.id.spDepartamento)
        val spProvincia = view.findViewById<AutoCompleteTextView>(R.id.spProvincia)
        val spDistrito = view.findViewById<AutoCompleteTextView>(R.id.spDistrito)
        val etDomicilio = view.findViewById<TextInputEditText>(R.id.etDomicilio)
        val btnNext = view.findViewById<MaterialButton>(R.id.btnSiguiente)

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

            Log.d("Paso2", "Nombre: $name, Apellidos: $paterno $materno, Sexo: $sexo, Seguro: $seguro, Dirección: $domicilio")

            (activity as RegisterActivity).openThirdFilterRecord()
        }
    }
}