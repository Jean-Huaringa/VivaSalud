package com.example.vivasalud

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText


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

        val spTypeDocument = view.findViewById<AutoCompleteTextView>(R.id.spTypeDocument)
        val etDocument = view.findViewById<TextInputEditText>(R.id.etDocument)
        val etBirthdate = view.findViewById<TextInputEditText>(R.id.etBirthdate)
        val checkBox = view.findViewById<MaterialCheckBox>(R.id.checkbox)
        val btnNext = view.findViewById<MaterialButton>(R.id.btnSiguientePaso)


        btnSiguiente.setOnClickListener {
            val typeDoc = spTypeDocument.text.toString()
            val document = etDocument.text.toString()
            val birthdate = etBirthdate.text.toString()
            val acceptedTerms = checkBox.isChecked


            Log.d("Paso1", "Tipo: $typeDoc, Documento: $document, Fecha: $birthdate, Términos: $acceptedTerms")

            (activity as RegisterActivity).openSecondFilterRecord()
        }
    }
}