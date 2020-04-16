package com.meme.model.dto

import com.google.gson.annotations.SerializedName

data class MemeDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title:String,
    @SerializedName("description")
    var description: String,
    @SerializedName("isFavourite")
    var isFavourite: Boolean,
    @SerializedName("createdDate")
    var createdDate: Int,
    @SerializedName("photoUrl")
    var photoUrl: String
)