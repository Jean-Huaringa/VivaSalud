package com.example.vivasalud.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName= "tb_cita")
data class Cita(
    @PrimaryKey val id: Int,
    var name: String = "",
    var day: Date,
    var time: Date,
    var clinica: String = "",
    var docto: String = ""
) : Parcelable
