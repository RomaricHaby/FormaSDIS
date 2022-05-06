package com.formasdis.ui.fragment.quiz.show_quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.model.Question
import com.formasdis.network.User
import com.formasdis.ui.fragment.HomeFragment
import com.formasdis.ui.fragment.quiz.AllQuizFragment

class Show1Question4ResponseFragment() : Fragment() {

    private lateinit var toolBarTitle: TextView

    private lateinit var questionName: TextView

    private lateinit var response1TextView: TextView
    private lateinit var response2TextView: TextView
    private lateinit var response3TextView: TextView
    private lateinit var response4TextView: TextView

    private lateinit var response1Button: Button
    private lateinit var response2Button: Button
    private lateinit var response3Button: Button
    private lateinit var response4Button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =
            inflater.inflate(R.layout.fragment_show_1question_4response_fragment, container, false)


        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        // ToolBar
        toolBarTitle.visibility = View.VISIBLE

        toolBarTitle.text = User.currentQuiz.quiz?.name ?: "erreur"
        toolBarTitle.textSize = 20F

        questionName = view.findViewById(R.id.questionName1Question4Response)
        response1TextView = view.findViewById(R.id.response1TextView)
        response2TextView = view.findViewById(R.id.response2TextView)
        response3TextView = view.findViewById(R.id.response3TextView)
        response4TextView = view.findViewById(R.id.response4TextView)

        response1Button = view.findViewById(R.id.response1Button)
        response2Button = view.findViewById(R.id.response2Button)
        response3Button = view.findViewById(R.id.response3Button)
        response4Button = view.findViewById(R.id.response4Button)

        initData(User.currentQuiz.currentQuestion)
    }

    private fun checkAnswer(positionAnswer: Int, currentQuestion: Question?) {
        if (currentQuestion?.listAnswer?.get(positionAnswer)?.correct == true) {
            User.currentQuiz.correctAnswer.add(true)
        } else {
            User.currentQuiz.correctAnswer.add(false)
        }
    }

    private fun getNextQuestion(currentQuestion: Question?) {
        var indexCurrentQuestion: Int? =
            User.currentQuiz.quiz?.listQuestions?.indexOf(currentQuestion)

        if (indexCurrentQuestion != null) {
            //Quiz not finish
            if (User.currentQuiz.quiz!!.listQuestions.size > indexCurrentQuestion++!!) {

                User.currentQuiz.currentQuestion =
                    User.currentQuiz.quiz?.listQuestions?.get(indexCurrentQuestion)

                initData(User.currentQuiz.currentQuestion)
            }
            //Quiz finish
            else {
                User.currentQuiz.isFinish = true
                loadFragment(HomeFragment())
            }
        }
    }

    private fun initData(currentQuestion: Question?) {
        // Otherwise the correct answer is always first
        currentQuestion?.listAnswer?.shuffle()

        questionName.text = currentQuestion?.nameQuestion ?: "Erreur"

        response1TextView.text = currentQuestion?.listAnswer?.get(0)?.answer ?: "Erreur"
        response2TextView.text = currentQuestion?.listAnswer?.get(1)?.answer ?: "Erreur"
        response3TextView.text = currentQuestion?.listAnswer?.get(2)?.answer ?: "Erreur"
        response4TextView.text = currentQuestion?.listAnswer?.get(3)?.answer ?: "Erreur"

        response1Button.setOnClickListener {
            checkAnswer(0,currentQuestion)
            getNextQuestion(currentQuestion)
        }

        response2Button.setOnClickListener {
            checkAnswer(1,currentQuestion)
            getNextQuestion(currentQuestion)
        }

        response3Button.setOnClickListener {
            checkAnswer(2,currentQuestion)
            getNextQuestion(currentQuestion)
        }

        response4Button.setOnClickListener {
            checkAnswer(3,currentQuestion)
            getNextQuestion(currentQuestion)
        }
    }

    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main_act, fragment)
            .setReorderingAllowed(true)
            .addToBackStack("detail").commit()
    }
}