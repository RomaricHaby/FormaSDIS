package com.formasdis.ui.fragment.quiz.show_quiz

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.model.Question
import com.formasdis.network.User

class ShowQuestion(private val showAnswer: Boolean) : Fragment() {

    private lateinit var toolBarTitle: TextView


    //1Image true false
    private lateinit var include1ImageTrueFalse: ConstraintLayout
    private lateinit var imageViewTrueFalse: ImageView

    private lateinit var questionTrueFalse: TextView

    private lateinit var button1TrueFalse: Button
    private lateinit var button2TrueFalse: Button

    //1Question4Answers
    private lateinit var include1Question4Answer: ConstraintLayout

    private lateinit var question1Question4Answers: TextView

    private lateinit var response1TextView1Question4Answers: TextView
    private lateinit var response2TextView1Question4Answers: TextView
    private lateinit var response3TextView1Question4Answers: TextView
    private lateinit var response4TextView1Question4Answers: TextView

    private lateinit var response1Button1Question4Answers: Button
    private lateinit var response2Button1Question4Answers: Button
    private lateinit var response3Button1Question4Answers: Button
    private lateinit var response4Button1Question4Answers: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =
            inflater.inflate(R.layout.fragment_show_question, container, false)


        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        //All component visibility gone
        resetView()

        setUpToolBar(view)

        //Get component layout
        include1ImageTrueFalse = view.findViewById(R.id.Component1imagetruefalse)
        include1Question4Answer = view.findViewById(R.id.Component1question4answers)

        //Show1Question4Response
        when (User.currentQuiz.currentQuestion?.type) {
            1 -> {}

            2 -> {
                include1Question4Answer.visibility = View.VISIBLE
                show1Question4Response(view)
            }

            3 -> {
                include1ImageTrueFalse.visibility = View.VISIBLE
                show1ImageTrueFalse(view)
            }
        }
    }

    //ToolBar
    private fun setUpToolBar(view: View) {
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        // ToolBar
        toolBarTitle.visibility = View.VISIBLE

        toolBarTitle.text = User.currentQuiz.quiz?.name ?: "erreur"
        toolBarTitle.textSize = 20F
    }

    //1Image true false
    private fun show1ImageTrueFalse(view: View) {
        imageViewTrueFalse = view.findViewById(R.id.image1Image)
        questionTrueFalse = view.findViewById(R.id.question1Image)
        button1TrueFalse = view.findViewById(R.id.response1Button1Image)
        button2TrueFalse = view.findViewById(R.id.response2Button1Image)

        initData1ImageTrueFalse(User.currentQuiz.currentQuestion)
    }

    private fun initData1ImageTrueFalse(currentQuestion: Question?) {
        questionTrueFalse.text = currentQuestion?.nameQuestion ?: "Erreur"


        if (showAnswer) {
            if (currentQuestion != null) {
                var i = 0
                while (i < currentQuestion.listAnswer.size) {
                    if (currentQuestion.listAnswer[i].correct) {
                        when (i) {
                            0 -> {
                                val nextColor = context?.let {
                                    ContextCompat.getColor(
                                        it, R.color.green
                                    )
                                }
                                button1TrueFalse.backgroundTintList =
                                    nextColor?.let {
                                        ColorStateList.valueOf(
                                            it
                                        )
                                    }
                            }

                            1 -> {
                                val nextColor = context?.let {
                                    ContextCompat.getColor(
                                        it, R.color.green
                                    )
                                }
                                button2TrueFalse.backgroundTintList =
                                    nextColor?.let {
                                        ColorStateList.valueOf(
                                            it
                                        )
                                    }
                            }
                        }
                    }
                    i++
                }
            }
        } else {
            button1TrueFalse.setOnClickListener {
                checkAnswer(0, currentQuestion)
                getNextQuestion(currentQuestion)
            }

            button2TrueFalse.setOnClickListener {
                checkAnswer(1, currentQuestion)
                getNextQuestion(currentQuestion)
            }
        }
    }

    //1Question4Response
    private fun show1Question4Response(view: View) {
        question1Question4Answers = view.findViewById(R.id.questionName1Question4Response)
        response1TextView1Question4Answers = view.findViewById(R.id.response1TextView)
        response2TextView1Question4Answers = view.findViewById(R.id.response2TextView)
        response3TextView1Question4Answers = view.findViewById(R.id.response3TextView)
        response4TextView1Question4Answers = view.findViewById(R.id.response4TextView)

        response1Button1Question4Answers = view.findViewById(R.id.response1Button)
        response2Button1Question4Answers = view.findViewById(R.id.response2Button)
        response3Button1Question4Answers = view.findViewById(R.id.response3Button)
        response4Button1Question4Answers = view.findViewById(R.id.response4Button)

        initData1Question4Response(User.currentQuiz.currentQuestion)
    }

    private fun initData1Question4Response(currentQuestion: Question?) {
        // Otherwise the correct answer is always first
        currentQuestion?.listAnswer?.shuffle()

        question1Question4Answers.text = currentQuestion?.nameQuestion ?: "Erreur"

        response1TextView1Question4Answers.text =
            currentQuestion?.listAnswer?.get(0)?.answer ?: "Erreur"
        response2TextView1Question4Answers.text =
            currentQuestion?.listAnswer?.get(1)?.answer ?: "Erreur"
        response3TextView1Question4Answers.text =
            currentQuestion?.listAnswer?.get(2)?.answer ?: "Erreur"
        response4TextView1Question4Answers.text =
            currentQuestion?.listAnswer?.get(3)?.answer ?: "Erreur"


        if (showAnswer) {
            if (currentQuestion != null) {
                var i = 0
                while (i < currentQuestion.listAnswer.size) {
                    if (currentQuestion.listAnswer[i].correct) {
                        when (i) {
                            0 -> {
                                val nextColor = context?.let {
                                    ContextCompat.getColor(
                                        it, R.color.green
                                    )
                                }
                                response1Button1Question4Answers.backgroundTintList =
                                    nextColor?.let {
                                        ColorStateList.valueOf(
                                            it
                                        )
                                    }
                            }

                            1 -> {
                                val nextColor = context?.let {
                                    ContextCompat.getColor(
                                        it, R.color.green
                                    )
                                }
                                response2Button1Question4Answers.backgroundTintList =
                                    nextColor?.let {
                                        ColorStateList.valueOf(
                                            it
                                        )
                                    }
                            }

                            2 -> {
                                val nextColor = context?.let {
                                    ContextCompat.getColor(
                                        it, R.color.green
                                    )
                                }
                                response3Button1Question4Answers.backgroundTintList =
                                    nextColor?.let {
                                        ColorStateList.valueOf(
                                            it
                                        )
                                    }
                            }

                            3 -> {
                                val nextColor = context?.let {
                                    ContextCompat.getColor(
                                        it, R.color.green
                                    )
                                }
                                response4Button1Question4Answers.backgroundTintList =
                                    nextColor?.let {
                                        ColorStateList.valueOf(
                                            it
                                        )
                                    }
                            }
                        }
                    }
                    i++
                }
            }
        } else {
            response1Button1Question4Answers.setOnClickListener {
                checkAnswer(0, currentQuestion)
                getNextQuestion(currentQuestion)
            }

            response2Button1Question4Answers.setOnClickListener {
                checkAnswer(1, currentQuestion)
                getNextQuestion(currentQuestion)
            }

            response3Button1Question4Answers.setOnClickListener {
                checkAnswer(2, currentQuestion)
                getNextQuestion(currentQuestion)
            }

            response4Button1Question4Answers.setOnClickListener {
                checkAnswer(3, currentQuestion)
                getNextQuestion(currentQuestion)
            }
        }
    }


    //Management quiz
    private fun getNextQuestion(currentQuestion: Question?) {
        var indexCurrentQuestion: Int? =
            User.currentQuiz.quiz?.listQuestions?.indexOf(currentQuestion)

        if (indexCurrentQuestion != null) {
            //Quiz not finish
            indexCurrentQuestion++
            if (User.currentQuiz.quiz!!.listQuestions.size > indexCurrentQuestion) {

                User.currentQuiz.currentQuestion =
                    User.currentQuiz.quiz?.listQuestions?.get(indexCurrentQuestion)

                initData1Question4Response(User.currentQuiz.currentQuestion)
            }
            //Quiz finish
            else {
                User.currentQuiz.isFinish = true
                loadFragment(ShowResultQuiz())
            }
        }
    }

    private fun checkAnswer(positionAnswer: Int, currentQuestion: Question?) {
        if (currentQuestion?.listAnswer?.get(positionAnswer)?.correct == true) {
            User.currentQuiz.correctAnswer.add(true)
        } else {
            User.currentQuiz.correctAnswer.add(false)
        }
    }

    private fun resetView() {
        include1ImageTrueFalse.visibility = View.GONE
        include1Question4Answer.visibility = View.GONE
    }

    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_current_quiz_act, fragment)
            .setReorderingAllowed(true)
            .commit()
    }
}