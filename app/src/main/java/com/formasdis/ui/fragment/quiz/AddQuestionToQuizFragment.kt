package com.formasdis.ui.fragment.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Quiz
import com.formasdis.ui.adapter.question_add.QuestionAddAdapter

class AddQuestionToQuizFragment(val quiz: Quiz) : Fragment() {

    private lateinit var toolBarTitle: TextView
    private lateinit var toolBarBack: ImageButton

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_question_to_quiz, container, false)

        initUI(view)

        onClick(view)

        return view
    }

    private fun onClick(view: View) {
        toolBarBack.setOnClickListener {
            loadFragment(AddQuizFragment())
        }
    }

    private fun initUI(view: View) {
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        toolBarBack = view.findViewById(R.id.imageButtonBack)

        // ToolBar
        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE

        toolBarTitle.text = "Créer un quiz"
        toolBarTitle.textSize = 20F



        recyclerView = view.findViewById(R.id.recyclerViewAddQuestionToQuiz)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val list = ArrayList<Int>()

        var i = 1
        while (i <= quiz.nbrQuestion) {
            list.add(i.toString().toInt())
            i++
        }

        val adapter = QuestionAddAdapter(list)
        adapter.also { recyclerView.adapter = it }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }


    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setReorderingAllowed(true)
            .addToBackStack("detail").commit()
    }
}