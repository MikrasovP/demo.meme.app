package com.meme.ui.login

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.meme.R
import com.meme.utils.PrefsEditor
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)

        PrefsEditor.build(applicationContext, packageName)

        initLoginButton()
        //initLoginTF()
        initPasswordTF()
    }

    private fun initLoginButton(){
        loginButton.setOnClickListener {
            presenter.onLoginButtonClicked(
                loginExtendedET.text.toString(),
                passwordExtendedET.text.toString()
            )
        }
    }

    private fun initLoginTF() {
        loginTF.setSimpleTextChangeWatcher { theNewText, isError ->
            presenter.onLoginTFChanged(
                theNewText,
                isError
            )
        }
    }

    private fun initPasswordTF() {
        passwordTF.endIconImageButton.setOnClickListener {
            onEyeIconClick()
        }
        passwordTF.helperText =
            resources.getString(R.string.password_length_tip).format(
                resources.getInteger(R.integer.password_length)
            )
        passwordExtendedET.inputType =
            InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
    }

    fun showLoginTFError() {
        loginTF.setError(
            resources.getString(R.string.empty_field_error),
            false
        )
    }

    fun showPasswordTFError() {
        passwordTF.setError(
            resources.getString(R.string.password_length_tip).format(
                resources.getInteger(R.integer.password_length)
            ),
            false
        )
    }

    fun showSnackError() {
        Snackbar.make(loginButton, R.string.login_error, Snackbar.LENGTH_LONG)
            .setBackgroundTint(
                ResourcesCompat.getColor(
                    resources,
                    R.color.colorSurfError,
                    theme
                )
            )
            .show()
    }

    fun showSpinnerOnButton() {
        loginButton.visibility = View.INVISIBLE
        loginProgressBar.visibility = View.VISIBLE
    }

    fun hideSpinnerOnButton() {
        loginProgressBar.visibility = View.INVISIBLE
        loginButton.visibility = View.VISIBLE

    }

    private fun onEyeIconClick() {
        if (passwordExtendedET.inputType ==
            InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
        ) {
            passwordTF.setEndIcon(R.drawable.ic_shown_password)
            passwordExtendedET.inputType =
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD or InputType.TYPE_CLASS_NUMBER
        } else {
            passwordTF.setEndIcon(R.drawable.ic_hidden_password)
            passwordExtendedET.inputType =
                InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
        }
        passwordExtendedET.setSelection(passwordExtendedET.length())
    }

}