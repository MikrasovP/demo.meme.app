package com.meme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.meme.R
import com.meme.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({moveToNextActivity()}, 300L)
    }

    private fun moveToNextActivity() {
        val intent = Intent(this, LoginActivity::class.java).apply {}
        startActivity(intent)
    }

}