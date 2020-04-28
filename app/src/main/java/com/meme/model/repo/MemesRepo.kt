package com.meme.model.repo

import com.meme.model.dto.AuthInfoDto
import com.meme.model.dto.LoginUserRequestDto
import com.meme.model.dto.MemeDto
import com.meme.model.service.MemesNetworkService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

object MemesRepo {
    private val service = MemesNetworkService

    fun auth(login: String, password: String): Observable<AuthInfoDto> =
        service.auth(LoginUserRequestDto(login, password))

    fun logout(): Completable = service.logout()

    fun getMemes(): Observable<List<MemeDto>> = service.getMemes()
}