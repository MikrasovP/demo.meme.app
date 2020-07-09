package com.meme.utils.di

import android.content.Context
import androidx.room.Room
import com.meme.model.database.AppDatabase
import com.meme.model.repo.DatabaseRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseRepoModule {
    companion object {
        @Singleton
        @Provides
        fun providesDatabaseRepo(appDatabase: AppDatabase) = DatabaseRepo(appDatabase)

        @Singleton
        @Provides
        fun providesAppDatabase(applicationContext: Context) =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
                .build()

    }
}