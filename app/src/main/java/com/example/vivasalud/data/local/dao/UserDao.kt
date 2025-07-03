package com.example.vivasalud.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.vivasalud.data.model.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)
    @Update
    fun update(user: User)
    @Delete
    fun delete(user: User)
    @Query("SELECT * FROM tb_user WHERE id = :id")
    fun getUserById(id: Long): User?
}