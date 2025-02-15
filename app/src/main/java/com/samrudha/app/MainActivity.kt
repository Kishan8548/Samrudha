package com.samrudha.app

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_samrudha)

        bottomNav = findViewById(R.id.bottom_navigation)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val menuIcon = findViewById<ImageButton>(R.id.menuIcon)
        val profileIcon = findViewById<ImageButton>(R.id.profileIcon)
        val titleText = findViewById<View>(R.id.toolbarTitle)

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)  // Disable default title

        // Open drawer when menu icon is clicked
        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Profile icon -> Open Profile Fragment and update Bottom Navigation selection
        profileIcon.setOnClickListener {
            loadFragment(ProfileFragment())
            bottomNav.selectedItemId = R.id.nav_profile // Update bottom navigation
            Log.d("ProfileFragment", "Profile icon clicked")
        }

        // Toolbar title -> Open Home Fragment
        titleText.setOnClickListener {
            loadFragment(HomeFragment())
            bottomNav.selectedItemId = R.id.nav_home // Ensure Home is selected
        }

        // Default: Load Home Fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
            bottomNav.selectedItemId = R.id.nav_home
        }

        // Handle Bottom Navigation
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_assistant -> loadFragment(AssistantFragment())
                R.id.nav_profile -> loadFragment(ProfileFragment())
            }
            true
        }

        // Handle Navigation Drawer Item Clicks
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    bottomNav.selectedItemId = R.id.nav_home
                }
                R.id.nav_assistant -> {
                    loadFragment(AssistantFragment())
                    bottomNav.selectedItemId = R.id.nav_assistant
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    bottomNav.selectedItemId = R.id.nav_profile
                }
                R.id.nav_settings -> loadFragment(ProfileFragment()) // Settings Fragment
                R.id.nav_logout -> Log.d("Logout", "User logged out")
            }
            drawerLayout.closeDrawers() // Close drawer after selection
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
