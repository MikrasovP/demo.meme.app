package com.meme.utils.di

import android.content.Context
import com.meme.utils.PrefsEditor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PrefsEditorModule {
    companion object {
        @Singleton
        @Provides
        fun providePrefsEditor(applicationContext: Context): PrefsEditor {
            val prefsEditor = PrefsEditor()
            prefsEditor.build(applicationContext, "com.meme")
            return prefsEditor
        }

    }
}