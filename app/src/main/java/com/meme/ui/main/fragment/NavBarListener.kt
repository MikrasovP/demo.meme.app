package com.meme.ui.main.fragment

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.meme.R

class NavBarListener(private val fragmentManager: FragmentManager) : BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment = when(item.itemId) {
            R.id.menu_feed -> FeedFragment()

            R.id.menu_adder -> AdderFragment()

            R.id.menu_profile -> ProfileFragment()

            else -> return false
        }

        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        return true
    }
}