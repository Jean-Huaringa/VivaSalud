package com.example.vivasalud.data.repository

import com.example.vivasalud.data.local.dao.UserDao
import com.example.vivasalud.data.model.User
import java.util.concurrent.Flow

class UserRepository(private val userDao: UserDao) {
    //     fun getAllUsers(): Flow<List<User>> = userDao.getA
    suspend fun insertUser(user:User){
        userDao.insert(user)
    }

    suspend fun updateUser(user:User){
        userDao.update(user)
    }

    suspend fun deleteUser(user:User){
        userDao.delete(user)
    }
    suspend fun getByIdUser(id:Int): User?{
        return userDao.getUserById(id)
    }
}