package com.formasdis.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.formasdis.R

class CurrentQuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_quiz)

    }

    //Initialise all item of the view
    private fun initUI() {
        //loadFragment(Show1Question4ResponseFragment(User.currentQuiz.currentQuestion))

    }

    //Management fragment
    /*private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_current_quiz_act, fragment)
            .setReorderingAllowed(true)
            .commit()
    }*/
}