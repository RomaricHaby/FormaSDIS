package com.formasdis.ui.fragment.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Quiz
import com.formasdis.network.DataQuiz
import com.formasdis.ui.adapter.quiz.QuizAdapter
import com.formasdis.ui.fragment.HomeFragment


class AllQuizFragment : Fragment() {
    // Get item in view
    private lateinit var toolBarTitle : TextView
    private lateinit var toolBarBack : ImageButton

    private lateinit var recycleViewSUAP: RecyclerView
    private lateinit var recycleViewOD: RecyclerView
    private lateinit var recycleViewINC: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all_quiz, container, false)

        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        toolBarTitle  = view.findViewById(R.id.titleToolBar)
        toolBarBack = view.findViewById(R.id.imageButtonBack)

        recycleViewINC = view.findViewById(R.id.recycler_view_inc)
        recycleViewOD = view.findViewById(R.id.recycler_view_od)
        recycleViewSUAP = view.findViewById(R.id.recycler_view_suap)


        configureRecyclerView(DataQuiz.listQuizINC,recycleViewINC)
        configureRecyclerView(DataQuiz.listQuizOD,recycleViewOD)
        configureRecyclerView(DataQuiz.listQuizSAP,recycleViewSUAP)

        // ToolBar
        toolBarBack.visibility= View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Quiz"
        toolBarTitle.textSize = 20F


        toolBarBack.setOnClickListener {
            loadFragment(HomeFragment())
        }

    }

    private fun configureRecyclerView(list: List<Quiz>, recyclerView: RecyclerView) {
        val adapter = QuizAdapter(list)
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