package com.meme.ui.login

import android.app.Activity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.meme.R
import com.meme.model.service.MemesNetworkService
import kotlinx.android.synthetic.main.activity_login.*
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes

class LoginActivity : AppCompatActivity() {

    val presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setButtonListener()
        initTextFieldLogin()
        initTextFieldPassword()
    }

    private fun showError(){
        Toast.makeText(
            this,
            R.string.login_error,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun setButtonListener() {
        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener { view -> onLoginButtonClicked(view) }
    }

    private fun initTextFieldLogin() {
        val loginTextFieldLogin: TextFieldBoxes = findViewById(R.id.loginTextFieldLogin)
        loginTextFieldLogin.setSimpleTextChangeWatcher { theNewText, isError ->
            onLoginFieldTextChanged(
                theNewText,
                isError
            )
        }
    }

    private fun initTextFieldPassword() {
        val loginTextFieldPassword: TextFieldBoxes = findViewById(R.id.loginTextFieldPassword)
        loginTextFieldPassword.endIconImageButton.setOnClickListener {
            onEyeIconClick(loginTextFieldPassword)
        }
        loginTextFieldPassword.helperText =
            "Пароль должен содержать %d цифр".format(
                resources.getInteger(R.integer.password_length)
            )
        loginTextFieldPasswordExtended.inputType =
            InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
    }

    private fun onLoginButtonClicked(view: View) {
        if (loginTextFieldPasswordExtended.length() == 8 &&
            loginTextFieldLoginExtended.length() != 0
        ) {
            showSpinner(view)
            sendAuthRequest()
        } else {
            loginTextFieldPassword.setError(
                resources.getString(R.string.empty_field_error), false
            )
            loginTextFieldLogin.setError(
                resources.getString(R.string.empty_field_error), false
            )
        }
    }

    private fun sendAuthRequest() {
        presenter.auth(loginTextFieldLoginExtended.text.toString(),
            loginTextFieldPasswordExtended.text.toString())
    }

    private fun showSpinner(view: View) {
        view.visibility = View.INVISIBLE
        val bar: ProgressBar = findViewById(R.id.loginProgressBar)
        bar.visibility = View.VISIBLE
    }

    private fun onLoginFieldTextChanged(newText: String, isError: Boolean) {
        //here should be PhoneNumber formatter
    }

    private fun onEyeIconClick(field: TextFieldBoxes) {
        if (loginTextFieldPasswordExtended.inputType ==
            InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
        ) {
            field.setEndIcon(R.drawable.ic_shown_password)
            loginTextFieldPasswordExtended.inputType =
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD or InputType.TYPE_CLASS_NUMBER
        } else {
            field.setEndIcon(R.drawable.ic_hidden_password)
            loginTextFieldPasswordExtended.inputType =
                InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
        }
        loginTextFieldPasswordExtended.setSelection(loginTextFieldPasswordExtended.length())
    }
}