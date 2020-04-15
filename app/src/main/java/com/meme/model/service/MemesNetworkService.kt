package com.meme.model.service

import com.meme.model.dto.AuthInfoDto
import com.meme.model.dto.LoginUserRequestDto
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MemesNetworkService {
    private var retrofit: Retrofit
    private const val BASE_URL = "https://demo2407529.mockable.io/"
    private var memesApi: MemesApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        memesApi = retrofit.create(
            MemesApi::class.java
        )
    }

    fun auth(
        login: String,
        password: String,
        onSuccess: (AuthInfoDto) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        memesApi.login(LoginUserRequestDto(login, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            onSuccess(it)
        }, {
            onError(it)
        })
    }

    fun logout() = memesApi.logout()
}