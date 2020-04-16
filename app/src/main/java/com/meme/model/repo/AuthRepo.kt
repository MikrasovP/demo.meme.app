package com.meme.model.repo

import com.meme.model.dto.AuthInfoDto
import com.meme.model.dto.LoginUserRequestDto
import com.meme.model.service.MemesNetworkService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

object AuthRepo {
    private val service = MemesNetworkService

    fun auth(login: String, password: String): Observable<AuthInfoDto> {
        return service.auth(LoginUserRequestDto(login, password))
    }

    fun logout(): Completable {
        return service.logout()
    }
}