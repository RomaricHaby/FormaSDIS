package com.formasdis.ui.fragment.quiz.add_quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.formasdis.R
import com.formasdis.model.Quiz
import com.formasdis.network.DataQuiz
import com.formasdis.ui.fragment.quiz.AllQuizFragment


class AddQuestionFragment(val quiz: Quiz, position: Int) : Fragment() {
    private lateinit var toolBarTitle: TextView
    private lateinit var toolBarBack: ImageButton



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_question, container, false)

        initUI(view)

        onClick(view)

        return view
    }

    private fun initUI(view: View) {
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        toolBarBack = view.findViewById(R.id.imageButtonBack)
        // ToolBar
        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE

        toolBarTitle.text = "Créer un quiz"
        toolBarTitle.textSize = 20F



    }


    private fun onClick(view: View) {

        toolBarBack.setOnClickListener {
            loadFragment(AddQuestionToQuizFragment(quiz))
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