package com.meme.ui.login

import android.content.Intent
import com.meme.R
import com.meme.model.dto.AuthInfoDto
import com.meme.model.repo.MemesNetRepo
import com.meme.ui.main.MainActivity
import com.meme.utils.App
import com.meme.utils.PrefsEditor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class LoginPresenter(
    private val activity: LoginActivity
) {
    @Inject
    lateinit var memesNetRepo : MemesNetRepo

    @Inject
    lateinit var prefsEditor: PrefsEditor

    init {
        val app: App = activity.application as App
        app.appComponent.inject(this)
    }

    private fun auth(login: String, password: String) {
        memesNetRepo.auth(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                setUserInfo(it)
            }, {
                onErrorResponse(it)
            })
    }

    private fun setUserInfo(authInfoDto: AuthInfoDto) {
        prefsEditor.setUser(authInfoDto)
        moveToMainActivity()
    }

    private fun moveToMainActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    private fun onErrorResponse(t: Throwable) {
        activity.showSnackError()
        t.printStackTrace()
        activity.hideSpinnerOnButton()
    }

    fun onLoginButtonClicked(login: String, password: String) {
        when {
            password.length !=
                    activity.resources.getInteger(R.integer.password_length) -> {
                activity.showPasswordTFError()
            }
            login.isEmpty() -> {
                activity.showLoginTFError()
            }
            else -> {
                activity.showSpinnerOnButton()
                auth(login, password)
            }
        }
    }
}