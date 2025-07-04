package com.example.vivasalud.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
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