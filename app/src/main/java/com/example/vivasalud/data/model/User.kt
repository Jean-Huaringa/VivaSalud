package com.example.vivasalud.data.model

import android.os.Parcelable
import android.view.View
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName= "tb_user")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
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

