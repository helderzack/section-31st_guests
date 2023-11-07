package com.helder.section_31_guests.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.android.material.navigation.NavigationView
import com.helder.section_31_guests.R
import com.helder.section_31_guests.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.appBarAndFragmentContainerViewLayout.toolbar)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        with(binding) {
            navView = navigationView

            val navHostFragment =
                supportFragmentManager
                    .findFragmentById(appBarAndFragmentContainerViewLayout.fragmentContainerView.id) as NavHostFragment

            drawerLayout = appDrawerLayout
            navController = navHostFragment.navController
            appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

            val toggle = ActionBarDrawerToggle(
                this@MainActivity, drawerLayout, appBarAndFragmentContainerViewLayout.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )

            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            onBackPressedDispatcher.addCallback {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    finish()
                }
            }

            navView.setCheckedItem(R.id.action_all_guests)
            navView.setNavigationItemSelectedListener(this@MainActivity)
        }
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_all_guests -> {
               navController.navigate(R.id.all_guests_fragment)
            }

            R.id.action_present_guests -> {
                navController.navigate(R.id.present_guests_fragment)

            }

            R.id.action_absent_guests -> {
                navController.navigate(R.id.absent_guests_fragment)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}