package com.samrudha.app

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_samrudha)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)  // Disable the default title



        val menuIcon = findViewById<ImageButton>(R.id.menuIcon)
        menuIcon.setOnClickListener {
            // Open navigation drawer or custom menu
        }


        val profileIcon = findViewById<ImageButton>(R.id.profileIcon)
        profileIcon.setOnClickListener {
            loadFragment(ProfileFragment())
            Log.d("ProfileFragment", "profileicon lcicked")
        }

        val titletext = findViewById<View>(R.id.toolbarTitle)
        titletext.setOnClickListener {
            loadFragment(HomeFragment())
        }

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }


        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_assistant -> loadFragment(AssistantFragment())
                R.id.nav_profile -> loadFragment(ProfileFragment())
            }
            true
        }
    }

    // Function to load a fragment into the container
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
