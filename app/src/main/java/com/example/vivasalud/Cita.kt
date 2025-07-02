package com.example.vivasalud

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cita(
    var name: String = ""
) : Parcelable
