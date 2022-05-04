package com.formasdis.ui.fragment.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.model.Question
import com.formasdis.model.Quiz
import java.lang.System.currentTimeMillis


class AddQuizFragment : Fragment() {

    private lateinit var toolBarTitle: TextView
    private lateinit var toolBarBack: ImageButton

    private lateinit var nameQuiz: EditText
    private lateinit var radioGroup: RadioGroup


    private lateinit var addQuestion: ImageButton
    private lateinit var removeQuestion: ImageButton
    private lateinit var nbrQuestion: TextView

    private lateinit var addQuestionButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_quiz, container, false)

        initUI(view)

        onClick(view)

        return view
    }


    private fun initUI(view: View) {
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        toolBarBack = view.findViewById(R.id.imageButtonBack)
        nameQuiz = view.findViewById(R.id.editTextTextNameQuizAdd)
        radioGroup = view.findViewById(R.id.radioGroupQuizAdd)

        addQuestion = view.findViewById(R.id.addQuestionQuizAdd)
        removeQuestion = view.findViewById(R.id.removeQuestionQuizAdd)

        nbrQuestion = view.findViewById(R.id.nbrQuestionAddQuiz)

        addQuestionButton = view.findViewById(R.id.buttonAddQuiz)

        // ToolBar
        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE

        toolBarTitle.text = "Créer un quiz"
        toolBarTitle.textSize = 20F
    }


    private fun onClick(view: View) {

        toolBarBack.setOnClickListener {
            loadFragment(AllQuizFragment())
        }

        addQuestion.setOnClickListener {
            var nbrQuestionInt  = nbrQuestion.text.toString().toInt()

            if(nbrQuestionInt == 10){
                Toast.makeText(context, "Le quiz ne peut pas dépasser 10 questions", Toast.LENGTH_LONG).show()
            }
            else{
                nbrQuestionInt++
            }

            nbrQuestion.text = nbrQuestionInt.toString()
        }

        removeQuestion.setOnClickListener {
            var nbrQuestionInt  = nbrQuestion.text.toString().toInt()

            if(nbrQuestionInt == 1){
                Toast.makeText(context, "Un quiz doit comporter une question", Toast.LENGTH_LONG).show()
            }
            else{
                nbrQuestionInt--
            }

            nbrQuestion.text = nbrQuestionInt.toString()
        }

        addQuestionButton.setOnClickListener {
            val id = currentTimeMillis().toInt()

            val selectedRadioButton = view.findViewById(radioGroup.checkedRadioButtonId) as RadioButton

            val type: String = selectedRadioButton.text.toString()


            val nbrQuestions = nbrQuestion.text.toString().toInt()
            val listQuestions = ArrayList<Question>()

            val name: String = nameQuiz.text.toString()

            val quiz = Quiz(id, name, nbrQuestions, type, listQuestions)

            //TODO envoyer en param l'object

            Toast.makeText(context, quiz.toString(), Toast.LENGTH_LONG).show()
            //loadFragment()

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