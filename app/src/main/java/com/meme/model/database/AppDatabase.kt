package com.meme.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MemeEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memeDao(): MemeDao
}