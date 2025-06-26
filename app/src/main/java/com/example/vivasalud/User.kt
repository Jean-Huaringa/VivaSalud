package com.example.vivasalud

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    val name: String,
    val paternalSurname: String,
    val maternalSurname: String,
    val sexo: String,
    val seguro: String,
    val pais: String,
    val department: String,
    val province: String,
    val district: String,
    val home: String,
    val typeDocument: String,
    val numberDocument: String,
    val birthdate: String
) : Parcelable

