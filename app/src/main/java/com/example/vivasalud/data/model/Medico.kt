package com.example.vivasalud.data.model

import androidx.room.PrimaryKey

data class Medico(
    val id: String = "",
    val nombre: String = "",
    val apellidoPat: String = "",
    val apellidoMat: String = "",
    val especialidad: String = "",
    val correo: String = "",
    val telefono: String = "",
    val clinica: String = ""
)
