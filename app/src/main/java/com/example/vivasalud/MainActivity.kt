package com.example.vivasalud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.os.Bundle
import com.example.vivasalud.data.model.Medico
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}