package com.meme.ui.login

import android.content.Intent
import com.meme.model.dto.AuthInfoDto
import com.meme.model.repo.MemesRepo
import com.meme.ui.main.MainActivity
import com.meme.utils.PrefsEditor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class LoginPresenter(
    private val activity: LoginActivity
) {
    private val authRepo = MemesRepo

    private fun auth(login: String, password: String) {
        authRepo.auth(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                setUserInfo(it)
            }, {
                onErrorResponse(it)
            })
    }

    private fun setUserInfo(authInfoDto: AuthInfoDto) {
        PrefsEditor.setUser(authInfoDto)
        moveToMainActivity()
    }

    private fun moveToMainActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }

    private fun onErrorResponse(t: Throwable) {
        activity.showSnackError()
        t.printStackTrace()
        activity.hideSpinnerOnButton()
    }

    fun onLoginButtonClicked(login: String, password: String) {
        when {
            password.length != 8 -> {
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

    fun onLoginTFChanged(newText: String, isError: Boolean) {
        //TODO("Phone num formatter is not implemented yet")
    }

}