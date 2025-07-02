package com.example.vivasalud.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vivasalud.data.local.dao.CitaDao
import com.example.vivasalud.data.local.dao.UserDao
import com.example.vivasalud.data.model.Cita
import com.example.vivasalud.data.model.User

@Database(
    entities = [User::class, Cita::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun citaDao(): CitaDao
}