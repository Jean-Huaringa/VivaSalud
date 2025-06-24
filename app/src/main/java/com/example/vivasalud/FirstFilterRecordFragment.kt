package com.example.vivasalud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.button.MaterialButton


class FirstFilterRecordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_first_filter_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSiguiente = view.findViewById<MaterialButton>(R.id.btnSiguientePaso)

        btnSiguiente.setOnClickListener {
            // Aquí puedes validar edad antes de continuar
            (activity as RegisterActivity).openSecondFilterRecord()
        }
    }
}