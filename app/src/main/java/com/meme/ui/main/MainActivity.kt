package com.meme.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.meme.R
import com.meme.ui.main.adder.AdderFragment
import com.meme.ui.main.feed.FeedFragment
import com.meme.ui.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var active: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.feed_toolbar))

        if (savedInstanceState == null) {
            initFragmentController()
        }
    }

    private fun initFragmentController() {
        val fm = supportFragmentManager
        val feedFragment = FeedFragment()
        val adderFragment = AdderFragment()
        val profileFragment = ProfileFragment()


        fm.beginTransaction()
            .add(R.id.fragment_container, profileFragment, "profile")
            .hide(profileFragment)
            .commit()

        fm.beginTransaction()
            .add(R.id.fragment_container, adderFragment, "adder")
            .hide(adderFragment)
            .commit()

        fm.beginTransaction()
            .add(R.id.fragment_container, feedFragment, "feed")
            .commit()

        nav_bar.setOnNavigationItemSelectedListener {
            onNavigationItemSelected(it, feedFragment, adderFragment, profileFragment)
        }

        //Following line prevent us from redundant fragment recreating
        nav_bar.setOnNavigationItemReselectedListener { }
    }


    /**
     * This method requires guarantee of not being called when same item had been selected
     */
    private fun onNavigationItemSelected(
        item: MenuItem,
        feedFragment: FeedFragment,
        adderFragment: AdderFragment,
        profileFragment: ProfileFragment
    ): Boolean {
        active = feedFragment

        when (item.itemId) {
            R.id.menu_feed ->{
                supportFragmentManager.beginTransaction().hide(active).show(feedFragment).commit()
                active = feedFragment
            }

            R.id.menu_adder -> {
                supportFragmentManager.beginTransaction().hide(active).show(adderFragment).commit()
                active = adderFragment
            }

            R.id.menu_profile -> {
                supportFragmentManager.beginTransaction().hide(active).show(profileFragment)
                    .commit()
                active = profileFragment
            }

            else -> return false
        }

        return true
    }

}