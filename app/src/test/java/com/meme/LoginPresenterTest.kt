package com.meme

import android.content.Context
import com.meme.model.repo.MemesNetRepo
import com.meme.ui.login.LoginActivity
import com.meme.ui.login.LoginPresenter
import com.meme.utils.App
import com.meme.utils.PrefsEditor
import com.meme.utils.di.AppComponent
import com.meme.utils.di.DaggerAppComponent
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginPresenterTest {

    companion object {
        private const val PROPER_LOGIN = "+79999999999"
        private const val PROPER_PASSWORD = "12345678"
    }

    private lateinit var mLoginPresenter: LoginPresenter

    @Mock
    private lateinit var mLoginActivity: LoginActivity

    @Mock
    private lateinit var mApp: App

    @Mock
    private lateinit var mApplicationContext: Context

    @Mock
    private lateinit var mAppComponent: AppComponent

    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(mAppComponent.inject(any(LoginPresenter::class.java))).thenAnswer { }
        Mockito.`when`(mApp.appComponent).thenReturn(mAppComponent)
        Mockito.`when`(mLoginActivity.application).thenReturn(mApp)

        mLoginPresenter = LoginPresenter(mLoginActivity)

    }

    @Test
    fun testEmptyPassword() {
        mLoginPresenter.onLoginButtonClicked(PROPER_LOGIN, "")
        Mockito.verify(mLoginActivity).showPasswordTFError()
    }

    /*@Test
    fun testProperLoginAndPassword() {

        Mockito.verify(mLoginActivity).showSpinnerOnButton()

        Mockito.`when`(mLoginPresenter.onLoginButtonClicked(PROPER_LOGIN, PROPER_PASSWORD))
            .then { mLoginActivity.showSpinnerOnButton() }

    }*/
}