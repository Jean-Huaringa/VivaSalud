package com.example.vivasalud.data.repository

import com.example.vivasalud.data.local.dao.CitaDao
import com.example.vivasalud.data.model.Cita

class CitaRepository(private val citaDao: CitaDao) {
    suspend fun insertUser(cita: Cita){
        citaDao.insert(cita)
    }

    suspend fun updateUser(cita: Cita){
        citaDao.update(cita)
    }

    suspend fun deleteUser(cita: Cita){
        citaDao.delete(cita)
    }
    suspend fun getByIdUser(id:Int): Cita?{
        return citaDao.getCitaById(id)
    }
}