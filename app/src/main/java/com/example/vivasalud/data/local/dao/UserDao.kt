package com.example.vivasalud.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.vivasalud.data.model.Usuario

@Dao
interface UserDao {
    @Insert
    fun insert(user: Usuario)
    @Update
    fun update(user: Usuario)
    @Delete
    fun delete(user: Usuario)
    @Query("SELECT * FROM tb_usuario WHERE id = :id")
    fun getUserById(id: Int): Usuario?
    @Query("SELECT * FROM tb_usuario WHERE typeDocument = :typeDocument AND numberDocument = :numberDocument AND password = :password LIMIT 1")
    fun login(typeDocument: String, numberDocument: String, password: String): Usuario?
}