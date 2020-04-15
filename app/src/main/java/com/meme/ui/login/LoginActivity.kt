package com.meme.ui.login

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    fun showError(){
        Toast.makeText(
            this,
            R.string.login_error,
            Toast.LENGTH_LONG
        ).show()
    }

    fun showSpinnerOnButton(view: View) {
        view.visibility = View.INVISIBLE
        loginProgressBar.visibility = View.VISIBLE
    }



}