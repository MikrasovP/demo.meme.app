package com.meme.utils

import android.app.Application
import androidx.room.Room
import com.meme.di.DaggerAppComponent
import com.meme.model.database.AppDatabase
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    companion object {
        lateinit var baseApplicationInstance: BaseApplication
            private set
    }

    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        baseApplicationInstance = this
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()

        PrefsEditor.build(applicationContext, "com.meme")
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    fun getDatabase(): AppDatabase {
        return database
    }
}