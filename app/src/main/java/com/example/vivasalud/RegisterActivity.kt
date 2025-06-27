package com.example.vivasalud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorRegistro, FirstFilterRecordFragment())
            .commit()
    }

    fun openSecondFilterRecord(name: JvmName) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorRegistro, SecondFilterRecordFragment())
            .addToBackStack(null)
            .commit()
    }

    fun openThirdFilterRecord() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorRegistro, ThirdFilterRecordFragment())
            .addToBackStack(null)
            .commit()
    }
}