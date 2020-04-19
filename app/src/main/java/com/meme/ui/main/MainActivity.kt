package com.meme.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meme.R
import com.meme.model.dto.MemeDto
import com.meme.ui.main.feed.FeedFragment
import com.meme.ui.main.feed.MemeCard
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_feed.*


class MainActivity : AppCompatActivity() {

    private val navBarListener: NavBarListener =
        NavBarListener(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navBar.setOnNavigationItemSelectedListener(navBarListener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainer,
                FeedFragment()
            ).commit()
        }

        val testCard = MemeCard(applicationContext)
        testCard.memeDto = MemeDto(
            0, "Test title", "Description test",
            true, 1111,
            "https://media.sproutsocial.com/uploads/meme-example.jpg"
        )
        fragmentContainer.addView(testCard)

    }

}