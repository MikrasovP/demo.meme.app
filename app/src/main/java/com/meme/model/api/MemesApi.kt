package com.meme.model.api

import com.meme.model.dto.MemeDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface MemesApi {
    @GET("/memes")
    fun memes(): Observable<List<MemeDto>>
}