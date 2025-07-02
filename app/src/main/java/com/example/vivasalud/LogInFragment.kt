package com.example.vivasalud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton

class LogInFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    private lateinit var btnEnviar: MaterialButton
    private lateinit var btnCrearCuenta: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEnviar = view.findViewById(R.id.btnEnviar)
        btnCrearCuenta = view.findViewById(R.id.btnCreateAccount)

        btnEnviar.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        btnCrearCuenta?.setOnClickListener {
            findNavController().navigate(R.id.firstFilterRecordFragment)
        }
    }
}