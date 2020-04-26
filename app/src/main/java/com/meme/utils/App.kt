package com.meme.utils

import android.app.Application
import androidx.room.Room
import com.meme.database.AppDatabase


class App : Application() {

    companion object{

        lateinit var appInstance: App

        fun getInstance(): App {
            return appInstance
        }
    }

    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
            .build()
    }

    fun getDatabase(): AppDatabase {
        return database
    }
}