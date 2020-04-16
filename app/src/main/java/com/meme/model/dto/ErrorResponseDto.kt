package com.meme.model.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponseDto(
    @SerializedName("code")
    var code: String,
    @SerializedName("errorMessage")
    var errorMessage: String
)