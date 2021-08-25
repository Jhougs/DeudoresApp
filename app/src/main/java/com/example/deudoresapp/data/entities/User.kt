package com.example.deudoresapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "table_User")
data class User (

    @PrimaryKey(autoGenerate= false ) @ColumnInfo(name= "UserName") val id: String,
    @ColumnInfo(name= "Email") val email : String,
    @ColumnInfo(name= "Password")val password: String,


    )