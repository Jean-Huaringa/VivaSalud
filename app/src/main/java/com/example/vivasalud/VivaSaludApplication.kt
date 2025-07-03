package com.example.vivasalud

import android.app.Application
import androidx.room.Room
import com.example.vivasalud.data.local.database.AppDatabase

class VivaSaludApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "AppDatabase")
            .build()
    }
}