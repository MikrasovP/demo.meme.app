package com.meme.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MemeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memeDao(): MemeDao
}