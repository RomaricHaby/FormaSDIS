package com.formasdis.ui.fragment.quiz.add_quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.model.Answer
import com.formasdis.model.Quiz


class AddQuestionFragment(val quiz: Quiz, private val position: Int) : Fragment() {

    //ToolBar
    private lateinit var toolBarTitle: TextView
    private lateinit var toolBarBack: ImageButton

    //base data
    private lateinit var spinner: Spinner
    private lateinit var nameQuestionEditTest: EditText
    private lateinit var buttonValidate: Button

    //1question 4 answers
    private lateinit var include1question4answer: ConstraintLayout
    private lateinit var answerGood: EditText
    private lateinit var falseAnswer: EditText
    private lateinit var false2Answer: EditText
    private lateinit var false3Answer: EditText

    //1image true false
    private lateinit var include1ImageTrueFalse: ConstraintLayout
    private lateinit var radioGroup: RadioGroup
    private lateinit var imageQuestion: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_question, container, false)

        initUI(view)

        onClick()

        setSpinner(view)
        resetInclude()

        return view
    }

    private fun resetInclude() {
        include1question4answer.visibility = View.GONE
        include1ImageTrueFalse.visibility = View.GONE
    }

    private fun setSpinner(view: View) {
        spinner = view.findViewById(R.id.spinner)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                resetInclude()

                when (parent?.getItemAtPosition(position)?.toString()) {
                    "1 question / 4 réponses images" -> {

                    }

                    "1 question / 4 réponses" -> {
                        include1question4answer.visibility = View.VISIBLE
                    }

                    "1 question / vrai ou faux" -> {
                        include1ImageTrueFalse.visibility = View.VISIBLE
                    }

                    "1 image / 4 réponses" -> {

                    }
                }
            }
        }
    }


    private fun initUI(view: View) {
        nameQuestionEditTest = view.findViewById(R.id.editTextNameQuestionAddQuestion)
        buttonValidate = view.findViewById(R.id.buttonValidateAddQuestion)

        //1 question 4 answers
        answerGood = view.findViewById(R.id.editTextGoodAnswerAddQuestion)
        falseAnswer = view.findViewById(R.id.editTextFalseAnswer1AddQuestion)
        false2Answer = view.findViewById(R.id.editTextFalseAnswer2AddQuestion)
        false3Answer = view.findViewById(R.id.editTextFalseAnswer3AddQuestion)
        include1question4answer = view.findViewById(R.id.component1question4answerAddQuestion)


        // true false
        include1ImageTrueFalse = view.findViewById(R.id.component1ImageTrueFalse)
        radioGroup = view.findViewById(R.id.radioGroup1imageTrueFalse)
        imageQuestion = view.findViewById(R.id.editTextURL1ImageTrueFalse)


        // ToolBar
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        toolBarBack = view.findViewById(R.id.imageButtonBack)

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
            if (nameQuestionEditTest.text.isNotBlank()) {

                quiz.listQuestions[position].type = spinner.selectedItemPosition

                quiz.listQuestions[position].nameQuestion = nameQuestionEditTest.text.toString()

                when (spinner.selectedItemPosition) {
                    0 -> {

                    }

                    1 -> {
                        if (answerGood.text.isNotBlank()
                            && falseAnswer.text.isNotBlank()
                            && false2Answer.text.isNotBlank()
                            && false3Answer.text.isNotBlank()
                        ) {
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
                            Toast.makeText(
                                context,
                                "Tous les champs ne sont pas remplie",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }

                    2 -> {
                        if (imageQuestion.text.isNotBlank()) {
                            val selectedRadioButton =
                                view?.findViewById(radioGroup.checkedRadioButtonId) as RadioButton

                            val goodAnswer: String = selectedRadioButton.text.toString()

                            if (goodAnswer.lowercase() == "vrais") {
                                quiz.listQuestions[position].listAnswer.add(Answer("vrais", true))
                                quiz.listQuestions[position].listAnswer.add(Answer("faux", false))
                            } else {
                                quiz.listQuestions[position].listAnswer.add(Answer("vrais", false))
                                quiz.listQuestions[position].listAnswer.add(Answer("faux", true))
                            }

                            quiz.listQuestions[position].urlImage = imageQuestion.text.toString()

                            loadFragment(AddQuestionToQuizFragment(quiz))
                        } else {
                            Toast.makeText(
                                context,
                                "Tous les champs ne sont pas remplie",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }
            } else {
                Toast.makeText(context, "Tous les champs ne sont pas remplie", Toast.LENGTH_LONG)
                    .show()
            }
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