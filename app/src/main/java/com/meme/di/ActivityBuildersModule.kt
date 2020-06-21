package com.meme.di

import com.meme.ui.login.LoginActivity
import com.meme.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity() : LoginActivity

}