package com.meme.utils.di

import android.content.Context
import com.meme.model.repo.DatabaseRepo
import com.meme.ui.login.LoginPresenter
import com.meme.ui.main.adder.AdderPresenter
import com.meme.ui.main.feed.FeedPresenter
import com.meme.ui.main.profile.ProfilePresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseRepoModule::class,
        PrefsEditorModule::class,
        MemesNetRepoModule::class
    ]
)
interface AppComponent {

    fun inject(adderPresenter: AdderPresenter)

    fun inject(profilePresenter: ProfilePresenter)

    fun inject(loginPresenter: LoginPresenter)

    fun inject(feedPresenter: FeedPresenter)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }
}