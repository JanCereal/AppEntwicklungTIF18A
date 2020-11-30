package com.example.AppEntwicklungTIF18A

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        val navigationBarBottom = findViewById<BottomNavigationView>(R.id.navBarBottom)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(navigationBarBottom, navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        val homeFragment = HomeFragment()
        val categoryFragment = CategoryFragment()
        val statsFragment = StatsFragment()
        val settingsFragment = SettingsFragment()

        replaceFragment(homeFragment)

        navBarBottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.imgHome -> replaceFragment(homeFragment)
                R.id.imgCategory -> replaceFragment(categoryFragment)
                R.id.imgStatistics -> replaceFragment(statsFragment)
                R.id.imgSettings -> replaceFragment(settingsFragment)
            }
            true
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

    private fun replaceFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }
}