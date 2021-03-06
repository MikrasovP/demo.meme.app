package com.meme.model.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MemeDto(
    @SerializedName("id")
    var id: Long = 0,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("isFavorite")
    var isFavourite: Boolean,
    @SerializedName("createdDate")
    var createdDate: Long,
    @SerializedName("photoUrl")
    var photoUrl: String
) : Serializable