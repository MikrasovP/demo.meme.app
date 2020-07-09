package com.meme.utils

import android.app.Application
import androidx.room.Room
import com.meme.model.database.AppDatabase
import com.meme.utils.di.AppComponent
import com.meme.utils.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appContext(applicationContext)
            .build()
    }
}