package com.example.deudoresapp

import android.app.Application
import androidx.room.Room
import com.example.deudoresapp.data.DebtorDatabase
import com.example.deudoresapp.data.UserDatabase

class DeudoresApp: Application() {

    companion object{
        lateinit var database: DebtorDatabase
        lateinit var database2: UserDatabase

    }

    override fun onCreate() {
        super.onCreate()

        database= Room.databaseBuilder(this,
        DebtorDatabase::class.java,"debtor_db" ).allowMainThreadQueries().build()

        database2= Room.databaseBuilder(this, UserDatabase::class.java,"User_db").allowMainThreadQueries().build()
    }
}