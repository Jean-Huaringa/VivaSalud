package com.example.vivasalud

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class ThirdFilterRecord : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_filter_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etClave = view.findViewById<TextInputEditText>(R.id.spClave)
        val etRepetirClave = view.findViewById<TextInputEditText>(R.id.spRepeatClave)
        val btnRegistrar = view.findViewById<MaterialButton>(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            val clave = etClave.text.toString()
            val repetirClave = etRepetirClave.text.toString()

            if (clave != repetirClave) {
                Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("Paso3", "Contraseña registrada correctamente: $clave")

        }
    }

}