package com.meme.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MemeEntity")
data class MemeEntity(
    @PrimaryKey
    var id: Long,
    var title: String,
    var description: String,
    var isFavourite: Boolean,
    var createdDate: Long,
    var photoUrl: String
)