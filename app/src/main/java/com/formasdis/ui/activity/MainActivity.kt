package com.formasdis.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.databinding.ActivityMainBinding
import com.formasdis.ui.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }


    //Initialise all item of the view
    private fun initUI() {
        navigation = findViewById(R.id.navBar)

        initBottomNavBar()
    }

    private fun initBottomNavBar() {
        //navigation.selectedItemId = R.id.navigation_home
        navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_education -> {} //loadFragment(HomeFragment())

                R.id.navigation_dashboard -> {}

                R.id.navigation_notifications -> {}
            }
            return@setOnItemSelectedListener true
        }
    }


    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setReorderingAllowed(true)
            .commit()
    }
}