package com.meme.ui.login

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.meme.R
import kotlinx.android.synthetic.main.activity_login.*
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes

class LoginActivity : AppCompatActivity() {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(
            this,
            loginButton,
            loginTF,
            passwordTF,
            loginExtendedET,
            passwordExtendedET
        )

    }

    fun showError() {
        Snackbar.make(loginButton, R.string.login_error, Snackbar.LENGTH_LONG)
            .setBackgroundTint(
                ResourcesCompat.getColor(
                    resources,
                    R.color.colorSurfBackgroundLighter,
                    theme
                )
            )
            .show()
    }

    fun showSpinnerOnButton(view: View) {
        view.visibility = View.INVISIBLE
        loginProgressBar.visibility = View.VISIBLE
    }

    fun hideSpinnerOnButton(view: View) {
        loginProgressBar.visibility = View.INVISIBLE
        view.visibility = View.VISIBLE

    }


}