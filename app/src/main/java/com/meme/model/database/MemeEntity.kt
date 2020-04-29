package com.meme.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MemeEntity")
data class MemeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var title: String,
    var description: String,
    var isFavourite: Boolean,
    var createdDate: Long,
    var photoUrl: String
)