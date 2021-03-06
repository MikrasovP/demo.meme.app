package com.meme.model.service

import com.meme.model.api.AuthApi
import com.meme.model.api.MemesApi
import com.meme.model.dto.AuthInfoDto
import com.meme.model.dto.LoginUserRequestDto
import com.meme.model.dto.MemeDto
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    companion object{
        private const val BASE_URL = "https://demo2407529.mockable.io/"
    }

    private val retrofit: Retrofit
    private val authApi: AuthApi
    private val memesApi: MemesApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        authApi = retrofit.create(AuthApi::class.java)
        memesApi = retrofit.create(MemesApi::class.java)
    }

    fun auth(requestDto: LoginUserRequestDto): Observable<AuthInfoDto> =
        authApi.login(requestDto)

    fun logout(): Completable =
        authApi.logout()

    fun getMemes(): Observable<List<MemeDto>> = memesApi.memes()
}