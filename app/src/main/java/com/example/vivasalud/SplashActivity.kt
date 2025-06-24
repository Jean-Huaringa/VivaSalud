package com.example.vivasalud

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class SplashActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Instala el splash screen antes de mostrar el contenido
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // Aquí puedes iniciar tu MainActivity directamente o hacer una transición
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}