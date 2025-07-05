package com.example.vivasalud.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "vivasalud_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}