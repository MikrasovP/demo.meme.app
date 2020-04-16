package com.meme.model.dto

import com.google.gson.annotations.SerializedName
import com.meme.model.UserInfo

data class AuthInfoDto(
    @SerializedName("accessToken")
    var accessToken: String,
    @SerializedName("userInfo")
    var userInfo: UserInfo
)