package com.meme.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.meme.R
import com.meme.ui.main.adder.AdderActivity
import com.meme.ui.main.feed.FeedFragment
import com.meme.ui.main.profile.ProfileFragment


class MainActivity : AppCompatActivity() {

    private lateinit var active: Fragment
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var feedFragment: FeedFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavView = findViewById(R.id.nav_bar)

        if (savedInstanceState == null) {
            initFragmentController()
        }
    }

    private fun initFragmentController() {
        val fm = supportFragmentManager
        feedFragment = FeedFragment()
        profileFragment = ProfileFragment()


        fm.beginTransaction()
            .add(R.id.fragment_container, profileFragment, "profile")
            .hide(profileFragment)
            .commit()

        fm.beginTransaction()
            .add(R.id.fragment_container, feedFragment, "feed")
            .commit()

        active = feedFragment
        bottomNavView.setOnNavigationItemSelectedListener {
            onNavigationItemSelected(it)
        }

        //Following line prevent us from redundant fragment recreating
        bottomNavView.setOnNavigationItemReselectedListener { }
    }


    /**
     * This method requires guarantee of not being called when same item had been selected
     */
    private fun onNavigationItemSelected(
        item: MenuItem
    ): Boolean {

        when (item.itemId) {
            R.id.menu_feed -> {
                supportFragmentManager.beginTransaction().hide(active).show(feedFragment).commit()
                active = feedFragment
            }

            R.id.menu_adder -> {
                val intent = Intent(this, AdderActivity::class.java)
                startActivity(intent)
                bottomNavView.selectedItemId = when(active){
                    is FeedFragment -> R.id.menu_feed
                    else -> R.id.menu_profile
                }
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