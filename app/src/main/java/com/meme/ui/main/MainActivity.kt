package com.meme.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meme.R
import com.meme.ui.main.feed.FeedFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val navBarListener: NavBarListener =
        NavBarListener(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.feedToolbar))

        navBar.setOnNavigationItemSelectedListener(navBarListener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainer,
                FeedFragment()
            ).commit()
        }

    }

}