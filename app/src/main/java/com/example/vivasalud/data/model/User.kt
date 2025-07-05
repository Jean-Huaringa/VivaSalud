package com.example.vivasalud.data.model

import android.os.Parcelable
import android.view.View
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName= "tb_user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
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
    val birthdate: String,
    val password: String
)

