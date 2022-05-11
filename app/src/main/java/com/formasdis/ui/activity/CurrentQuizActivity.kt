package com.formasdis.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.network.DataUser
import com.formasdis.network.User
import com.formasdis.ui.fragment.quiz.show_quiz.ShowQuestion

class CurrentQuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_quiz)

        initUI()
    }

    //Initialise all item of the view
    private fun initUI() {
        loadFragment(ShowQuestion(false))
    }

    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_current_quiz_act, fragment)
            .setReorderingAllowed(true)
            .commit()
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Attention")
        builder.setMessage("Voulez-vous quitter le quiz ? Si vous en commencez un autre votre progression sera perdue")

        builder.setPositiveButton("Quitter") { _, _ ->
            super.onBackPressed()

            DataUser.updateCurrentQuiz()

            User.currentQuiz.quiz = null
            User.currentQuiz.currentQuestion = null
            User.currentQuiz.isFinish = false
            User.currentQuiz.correctAnswer.clear()
        }

        builder.setNegativeButton("Annuler") { _, _ ->
        }

        builder.show()
    }
}