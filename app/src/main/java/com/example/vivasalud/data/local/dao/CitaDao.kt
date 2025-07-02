package com.example.vivasalud.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.vivasalud.data.model.Cita
import com.example.vivasalud.data.model.User

@Dao
interface CitaDao {
    @Insert
    suspend fun insert(cita: Cita)
    @Update
    suspend fun update(cita: Cita)
    @Delete
    suspend fun delete(cita: Cita)
    @Query("SELECT * FROM tb_cita WHERE id = :id")
    suspend fun getCitaById(id: Int): Cita?
}