package com.meme.utils.di

import com.meme.model.repo.MemesNetRepo
import com.meme.model.service.NetworkService
import dagger.Module
import dagger.Provides

@Module
class MemesNetRepoModule {

    @Provides
    fun provideMemesNetRepo(service: NetworkService) = MemesNetRepo(service)

    @Provides
    fun providesNetworkService() = NetworkService()
}