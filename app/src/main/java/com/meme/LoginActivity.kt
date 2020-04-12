package com.meme

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar

class LoginActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onLoginButtonClicked(view: View){
        view.visibility = View.INVISIBLE
        val bar:ProgressBar = findViewById(R.id.loginProgressBar)
        bar.visibility = View.VISIBLE
    }
}