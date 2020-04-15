package com.meme.model.dto

data class MemeDto(
    var id: Int,
    var title:String,
    var description: String,
    var isFavourite: Boolean,
    var createdDate: Int,
    var photoUrl: String
)