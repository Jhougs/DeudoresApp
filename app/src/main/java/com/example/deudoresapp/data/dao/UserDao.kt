package com.example.deudoresapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.deudoresapp.data.entities.User


@Dao
interface UserDao {

    @Insert
    fun createUser(user: User)

    @Query("SELECT Email FROM table_User")
    fun getUserName(): MutableList<String>

    @Query("SELECT Password FROM TABLE_USER")
    fun getUserPassWord(): MutableList<String>

}