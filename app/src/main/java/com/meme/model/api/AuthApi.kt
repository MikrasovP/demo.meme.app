package com.meme.model.api

import com.meme.model.dto.AuthInfoDto
import com.meme.model.dto.LoginUserRequestDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    fun login(
        @Body body: LoginUserRequestDto
    ): Observable<AuthInfoDto>

    @POST("auth/logout")
    fun logout() : Completable
}