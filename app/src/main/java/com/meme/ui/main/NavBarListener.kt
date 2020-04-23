package com.meme.ui.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.meme.R
import com.meme.ui.main.adder.AdderFragment
import com.meme.ui.main.feed.FeedFragment
import com.meme.ui.main.profile.ProfileFragment

class NavBarListener(private val fragmentManager: FragmentManager) : BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment = when(item.itemId) {
            R.id.menu_feed -> FeedFragment()

            R.id.menu_adder -> AdderFragment()

            R.id.menu_profile -> ProfileFragment()

            else -> return false
        }

        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        return true
    }
}