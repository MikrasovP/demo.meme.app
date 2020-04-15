package com.meme.model.service

import com.meme.model.dto.AuthInfoDto
import com.meme.model.dto.LoginUserRequestDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MemesApi {
    @POST("auth/login")
    fun login(
        @Body body: LoginUserRequestDto
    ): Call<AuthInfoDto>

    @POST("auth/logout")
    fun logout()
}