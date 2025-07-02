package com.example.vivasalud

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    var name: String = "",
    var paternalSurname: String = "",
    var maternalSurname: String = "",
    var sexo: String = "",
    var seguro: String = "",
    var pais: String = "",
    var department: String = "",
    var province: String = "",
    var district: String = "",
    var home: String = "",
    var typeDocument: String = "",
    var numberDocument: String = "",
    var birthdate: String = "",
    var password: String = ""
) : Parcelable

