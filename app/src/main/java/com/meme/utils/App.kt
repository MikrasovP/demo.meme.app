package com.meme.utils

import android.app.Application
import androidx.room.Room
import com.meme.model.database.AppDatabase

class App : Application() {

    companion object {
        lateinit var appInstance: App
            private set
    }

    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()

        PrefsEditor.build(applicationContext, "com.meme")
    }

    fun getDatabase(): AppDatabase {
        return database
    }
}