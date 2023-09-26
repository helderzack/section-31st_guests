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
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.databinding.ActivityMainBinding
import com.helder.section_31_guests.ui.fragments.GuestsFragment
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModel

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setSupportActionBar(appBarAndFragmentContainerViewLayout.toolbar)
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

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, GuestsFragment()).commit()
            }

            navView.setNavigationItemSelectedListener(this@MainActivity)
            setContentView(root)
        }
    }

    override fun onResume() {
        super.onResume()
        navView.setCheckedItem(R.id.action_all_guests)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_all_guests -> {
                GuestsViewModel.getInstance(null).filterGuests(null)
            }

            R.id.action_present_guests -> {
                GuestsViewModel.getInstance(null).filterGuests(GuestStatus.Present)
            }

            R.id.action_absent_guests -> {
                GuestsViewModel.getInstance(null).filterGuests(GuestStatus.Absent)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }


}