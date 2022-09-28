package com.majd.techestisharatiassignment.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.majd.techestisharatiassignment.R
import com.majd.techestisharatiassignment.ui.fragments.home.HomeFragment
import com.majd.techestisharatiassignment.ui.fragments.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // Fragments
    var homeFragment = HomeFragment()
    var profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init bottom navigation bar actions
        bottom_navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> loadFragment(homeFragment)
                R.id.profile -> loadFragment(profileFragment)
            }
            true
        }

        // Set home fragment as default
        bottom_navigation.selectedItemId = R.id.home
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

}