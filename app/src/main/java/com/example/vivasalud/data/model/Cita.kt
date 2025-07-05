package com.example.vivasalud.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity(tableName= "tb_cita")
data class Cita(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: String,
    val area: String,
    val clinica: String,
    val doctor: String,
    val usuarioId: Int
)
