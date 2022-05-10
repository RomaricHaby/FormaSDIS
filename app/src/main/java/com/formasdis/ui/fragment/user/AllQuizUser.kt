package com.formasdis.ui.fragment.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Quiz
import com.formasdis.network.DataQuiz
import com.formasdis.network.User
import com.formasdis.ui.adapter.myquiz.MyQuizAdapter
import com.formasdis.ui.adapter.quiz.QuizAdapter
import com.formasdis.ui.fragment.HomeFragment
import com.formasdis.ui.fragment.quiz.add_quiz.AddQuizFragment
import com.google.firebase.auth.FirebaseAuth


class AllQuizUser : Fragment() {

    // Get item in view
    private lateinit var toolBarTitle: TextView
    private lateinit var toolBarBack: ImageButton
    private lateinit var toolBarAddQuiz: ImageButton

    private lateinit var recycleViewMyQuiz: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all_quiz_user, container, false)

        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        toolBarBack = view.findViewById(R.id.imageButtonBack)
        toolBarAddQuiz = view.findViewById(R.id.imageButtonAddQuizz)

        recycleViewMyQuiz = view.findViewById(R.id.recyclerViewMyQuiz)

        configureRecyclerView(DataQuiz.listQuizUser, recycleViewMyQuiz)

        // ToolBar
        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE
        toolBarAddQuiz.visibility = View.VISIBLE

        toolBarTitle.text = "Quiz"
        toolBarTitle.textSize = 20F


        toolBarBack.setOnClickListener {
            loadFragment(HomeFragment())
        }

        toolBarAddQuiz.setOnClickListener {
            loadFragment(AddQuizFragment())
        }


    }

    private fun configureRecyclerView(list: List<Quiz>, recyclerView: RecyclerView) {
        val adapter = context?.let { MyQuizAdapter(list, it) }
        adapter.also { recyclerView.adapter = it }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }


    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main_act, fragment)
            .setReorderingAllowed(true)
            .addToBackStack("detail").commit()
    }
}