package com.meme.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.text.InputType
import android.view.View
import android.widget.Button
import com.meme.R
import com.meme.model.dto.AuthInfoDto
import com.meme.model.service.MemesNetworkService
import com.meme.ui.main.MainActivity
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes


class LoginPresenter(
    private val activity: LoginActivity,
    private val loginButton: Button,
    private val loginTF: TextFieldBoxes,
    private val passwordTF: TextFieldBoxes,
    private val loginExtendedET: ExtendedEditText,
    private val passwordExtendedET: ExtendedEditText
) {

    companion object {
        private const val TOKEN_KEY = "token"
        private const val USER_ID_KEY = "id"
        private const val USERNAME_KEY = "username"
        private const val FIRST_NAME_KEY = "firstName"
        private const val LAST_NAME_KEY = "lastName"
        private const val USER_DESCRIPTION_KEY = "userDescription"
    }

    private var isLoggedIn: Boolean = false
    private val service = MemesNetworkService

    init {
        loginButton.setOnClickListener { view -> onLoginButtonClicked(view) }
        initLoginTF()
        initPasswordTF()
    }

    private fun auth(login: String, password: String) {
        service.auth(
            login,
            password,
            { setUserInfo(it) },
            { onErrorResponse(it) }
        )
    }

    private fun setUserInfo(authInfoDto: AuthInfoDto) {
        val shPref = activity.getPreferences(Context.MODE_PRIVATE)
        val ed: SharedPreferences.Editor = shPref.edit()
        ed.putString(TOKEN_KEY, authInfoDto.accessToken)
        ed.putInt(USER_ID_KEY, authInfoDto.userInfo.id)
        ed.putString(USERNAME_KEY, authInfoDto.userInfo.username)
        ed.putString(FIRST_NAME_KEY, authInfoDto.userInfo.firstName)
        ed.putString(LAST_NAME_KEY, authInfoDto.userInfo.lastName)
        ed.putString(USER_DESCRIPTION_KEY, authInfoDto.userInfo.userDescription)
        ed.apply()
        isLoggedIn = true
        moveToMainActivity()
    }

    private fun moveToMainActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }

    private fun onErrorResponse(t: Throwable) {
        isLoggedIn = false
        activity.showError()
        t.printStackTrace()
        activity.hideSpinnerOnButton(loginButton)
    }

    private fun onLoginButtonClicked(view: View) {
        when {
            passwordExtendedET.length() != 8 -> {
                passwordTF.setError(
                    "Пароль должен содержать %d цифр".format(
                        activity.resources.getInteger(R.integer.password_length)
                    ),
                    false
                )

            }
            loginExtendedET.length() == 0 -> {
                loginTF.setError(
                    activity.applicationContext.resources.getString(R.string.empty_field_error),
                    false
                )
            }
            else -> {
                activity.showSpinnerOnButton(view)
                auth(
                    loginExtendedET.text.toString(),
                    passwordExtendedET.text.toString()
                )
            }
        }
    }

    private fun initLoginTF() {
        loginTF.setSimpleTextChangeWatcher { theNewText, isError ->
            onLoginTFChanged(
                theNewText,
                isError
            )
        }
    }

    private fun onLoginTFChanged(newText: String, isError: Boolean) {
        //here should be PhoneNumber formatter
    }

    private fun initPasswordTF() {
        passwordTF.endIconImageButton.setOnClickListener {
            onEyeIconClick(passwordTF)
        }
        passwordTF.helperText =
            "Пароль должен содержать %d цифр".format(
                activity.resources.getInteger(R.integer.password_length)
            )
        passwordExtendedET.inputType =
            InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
    }

    private fun onEyeIconClick(field: TextFieldBoxes) {
        if (passwordExtendedET.inputType ==
            InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
        ) {
            field.setEndIcon(R.drawable.ic_shown_password)
            passwordExtendedET.inputType =
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD or InputType.TYPE_CLASS_NUMBER
        } else {
            field.setEndIcon(R.drawable.ic_hidden_password)
            passwordExtendedET.inputType =
                InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
        }
        passwordExtendedET.setSelection(passwordExtendedET.length())
    }

}