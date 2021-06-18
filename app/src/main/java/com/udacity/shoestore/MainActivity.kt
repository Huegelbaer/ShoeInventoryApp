package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val navController = getNavController()

        val appBarConfiguration = AppBarConfiguration
            // Define top level destinations to hide navigation up button
            .Builder(
                R.id.loginFragment,
                R.id.welcomeFragment,
                R.id.shoeListFragment
            )
            .build()

        val toolbar: Toolbar = binding.toolbar
        toolbar.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(toolbar)

        NavigationUI.setupActionBarWithNavController(this,
            navController,
            appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return getNavController().navigateUp()
    }

    private fun getNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}
