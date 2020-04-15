package com.meme.model.dto

import com.google.gson.annotations.SerializedName

data class LoginUserRequestDto(
    @SerializedName("login")
    var login: String,
    @SerializedName("password")
    var password: String
)