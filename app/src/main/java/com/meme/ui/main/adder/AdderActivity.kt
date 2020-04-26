package com.meme.ui.main.adder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meme.R

class AdderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adder)
        setSupportActionBar(findViewById(R.id.adder_toolbar))
    }
}