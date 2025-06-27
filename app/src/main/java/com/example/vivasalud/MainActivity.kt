package com.example.vivasalud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var spTypeDocument: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        spTypeDocument = findViewById(R.id.spTypeDocuments)

        val typeDocument = listOf("DNI", "CE", "Pasaporte")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, typeDocument)
        spTypeDocument.setAdapter(adapter)

        val btnCreateAccount = findViewById<TextView>(R.id.btnCreateAccount)
        val btnSiguiente = findViewById<TextView>(R.id.btnEnviar)

        btnCreateAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnSiguiente.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}