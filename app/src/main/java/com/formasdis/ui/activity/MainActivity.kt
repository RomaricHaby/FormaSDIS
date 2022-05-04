package com.formasdis.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.network.DataQuiz
import com.formasdis.ui.fragment.EducationFragment
import com.formasdis.ui.fragment.QuizFragment
import com.formasdis.ui.fragment.RecruitmentFragment
import com.formasdis.ui.fragment.quiz.AllQuizFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataQuiz.getAllQuiz()

        initUI()
    }


    //Initialise all item of the view
    private fun initUI() {
        navigation = findViewById(R.id.navBar)

        initBottomNavBar()
    }

    private fun initBottomNavBar() {
        resetNavBar()
        navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_education -> loadFragment(EducationFragment())

                R.id.navigation_quiz -> loadFragment(AllQuizFragment())

                R.id.navigation_notifications -> loadFragment(RecruitmentFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    fun resetNavBar(){
        navigation.selectedItemId = R.id.invisible
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