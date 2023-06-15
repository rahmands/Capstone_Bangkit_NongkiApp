package com.rahman.nongki.view.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rahman.nongki.R
import com.rahman.nongki.databinding.ActivityBottomNavBinding
import com.rahman.nongki.model.ViewModelFactory

class BottomNavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavBinding
    private lateinit var bottomNavViewModel: BottomNavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        bottomNavViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[BottomNavViewModel::class.java]
        setContentView(binding.root)
        bottomNavViewModel.key.observe(this){
            Log.e("key", it)
            if (it.isNotEmpty()) {
                bottomNavViewModel.getRecommendationData(it)
            }
        }

        bottomNavViewModel.favorite

        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_search,
            R.id.navigation_wishlist,
            R.id.navigation_setting
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}