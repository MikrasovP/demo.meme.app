package com.meme.model.dto

import com.meme.model.UserInfo

data class AuthInfoDto(
    var accessToken: String,
    var userInfo: UserInfo
)