package com.meme.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id")
    var id: Int,
    @SerializedName("username")
    var username: String,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("userDescription")
    var userDescription: String
)