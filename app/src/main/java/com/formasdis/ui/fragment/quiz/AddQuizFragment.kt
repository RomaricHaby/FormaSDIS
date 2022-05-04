package com.formasdis.ui.fragment.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.formasdis.R

class AddQuizFragment : Fragment() {

    private lateinit var toolBarTitle: TextView
    private lateinit var toolBarBack: ImageButton

    private lateinit var nameQuiz: EditText
    private lateinit var radioGroup: RadioGroup


    private lateinit var addQuestion: ImageButton
    private lateinit var removeQuestion: ImageButton
    private lateinit var nbrQuestion: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_quiz, container, false)

        initUI(view)

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
        
        // ToolBar
        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE

        toolBarTitle.text = "Créer un quiz"
        toolBarTitle.textSize = 20F


        toolBarBack.setOnClickListener {
            loadFragment(AllQuizFragment())
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