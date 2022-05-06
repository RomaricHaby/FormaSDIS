package com.formasdis.ui.fragment.quiz.add_quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.model.Answer
import com.formasdis.model.Quiz


class AddQuestionFragment(val quiz: Quiz, val position: Int) : Fragment() {
    private lateinit var toolBarTitle: TextView
    private lateinit var toolBarBack: ImageButton

    private lateinit var spinner: Spinner

    private lateinit var nameQuestionEditTest: EditText
    private lateinit var answerGood: EditText
    private lateinit var falseAnswer: EditText
    private lateinit var false2Answer: EditText
    private lateinit var false3Answer: EditText

    private lateinit var buttonValidate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_question, container, false)

        initUI(view)

        onClick()

        return view
    }

    private fun initUI(view: View) {
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        toolBarBack = view.findViewById(R.id.imageButtonBack)
        spinner = view.findViewById(R.id.spinner)
        nameQuestionEditTest = view.findViewById(R.id.editTextNameQuestionAddQuestion)

        answerGood = view.findViewById(R.id.editTextGoodAnswerAddQuestion)
        falseAnswer = view.findViewById(R.id.editTextFalseAnswer1AddQuestion)
        false2Answer = view.findViewById(R.id.editTextFalseAnswer2AddQuestion)
        false3Answer = view.findViewById(R.id.editTextFalseAnswer3AddQuestion)


        buttonValidate = view.findViewById(R.id.buttonValidateAddQuestion)

        // ToolBar
        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE

        toolBarTitle.text = "Créer un quiz"
        toolBarTitle.textSize = 20F
    }


    private fun onClick() {

        toolBarBack.setOnClickListener {
            loadFragment(AddQuestionToQuizFragment(quiz))
        }

        buttonValidate.setOnClickListener {
            if (nameQuestionEditTest.text.isNotBlank()
                && answerGood.text.isNotBlank()
                && falseAnswer.text.isNotBlank()
                && false2Answer.text.isNotBlank()
                && false3Answer.text.isNotBlank()
            ) {

                quiz.listQuestions[position].type = spinner.selectedItemPosition

                quiz.listQuestions[position].nameQuestion = nameQuestionEditTest.text.toString()

                quiz.listQuestions[position].listAnswer.add(
                    Answer(
                        answerGood.text.toString(),
                        true
                    )
                )
                quiz.listQuestions[position].listAnswer.add(
                    Answer(
                        false2Answer.text.toString(),
                        false
                    )
                )
                quiz.listQuestions[position].listAnswer.add(
                    Answer(
                        false3Answer.text.toString(),
                        false
                    )
                )
                quiz.listQuestions[position].listAnswer.add(
                    Answer(
                        falseAnswer.text.toString(),
                        false
                    )
                )

                loadFragment(AddQuestionToQuizFragment(quiz))
            } else {
                Toast.makeText(context, "Tous les champs ne sont pas remplie", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setReorderingAllowed(true)
            .addToBackStack("detail").commit()
    }
}