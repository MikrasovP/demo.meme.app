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
        login_button.setOnClickListener {
            presenter.onLoginButtonClicked(
                login_ext_et.text.toString(),
                password_extended_et.text.toString()
            )
        }
    }

    private fun initLoginTF() {
        login_tf.setSimpleTextChangeWatcher { theNewText, isError ->
            presenter.onLoginTFChanged(
                theNewText,
                isError
            )
        }
    }

    private fun initPasswordTF() {
        password_tf.endIconImageButton.setOnClickListener {
            onEyeIconClick()
        }
        password_tf.helperText =
            resources.getString(R.string.password_length_tip).format(
                resources.getInteger(R.integer.password_length)
            )
        password_extended_et.inputType =
            InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
    }

    fun showLoginTFError() {
        login_tf.setError(
            resources.getString(R.string.empty_field_error),
            false
        )
    }

    fun showPasswordTFError() {
        password_tf.setError(
            resources.getString(R.string.password_length_tip).format(
                resources.getInteger(R.integer.password_length)
            ),
            false
        )
    }

    fun showSnackError() {
        Snackbar.make(login_button, R.string.login_error, Snackbar.LENGTH_LONG)
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
        login_button.visibility = View.INVISIBLE
        login_progress_bar.visibility = View.VISIBLE
    }

    fun hideSpinnerOnButton() {
        login_progress_bar.visibility = View.INVISIBLE
        login_button.visibility = View.VISIBLE

    }

    private fun onEyeIconClick() {
        if (password_extended_et.inputType ==
            InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
        ) {
            password_tf.setEndIcon(R.drawable.ic_shown_password)
            password_extended_et.inputType =
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD or InputType.TYPE_CLASS_NUMBER
        } else {
            password_tf.setEndIcon(R.drawable.ic_hidden_password)
            password_extended_et.inputType =
                InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
        }
        password_extended_et.setSelection(password_extended_et.length())
    }

}