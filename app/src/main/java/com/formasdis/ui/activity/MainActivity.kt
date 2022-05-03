package com.formasdis.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.ui.fragment.EducationFragment
import com.formasdis.ui.fragment.QuizFragment
import com.formasdis.ui.fragment.RecruitmentFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }


    //Initialise all item of the view
    private fun initUI() {
        navigation = findViewById(R.id.navBar)

        initBottomNavBar()
    }

    private fun initBottomNavBar() {
        //navigation.selectedItemId = R.id.navigation_education
        navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_education -> loadFragment(EducationFragment())

                R.id.navigation_quiz -> loadFragment(QuizFragment())

                R.id.navigation_notifications -> loadFragment(RecruitmentFragment())
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