package com.example.vivasalud.data.repository

import com.example.vivasalud.data.local.dao.UserDao
import com.example.vivasalud.data.model.Usuario

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user:Usuario){
        userDao.insert(user)
    }
    suspend fun updateUser(user:Usuario){
        userDao.update(user)
    }

    suspend fun deleteUser(user:Usuario){
        userDao.delete(user)
    }
    suspend fun getByIdUser(id:Int): Usuario?{
        return userDao.getUserById(id)
    }
    suspend fun getUserByLogin(typeDocument: String, document: String, clave: String): Usuario? {
        return userDao.login(typeDocument, document, clave)
    }
}