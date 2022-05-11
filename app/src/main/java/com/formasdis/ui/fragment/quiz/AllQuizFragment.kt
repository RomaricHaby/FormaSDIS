package com.formasdis.ui.fragment.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Quiz
import com.formasdis.network.DataQuiz
import com.formasdis.ui.adapter.quiz.QuizAdapter
import com.formasdis.ui.fragment.HomeFragment
import com.formasdis.ui.fragment.quiz.add_quiz.AddQuizFragment
import com.formasdis.ui.fragment.user.AllQuizUserFragment
import com.google.firebase.auth.FirebaseAuth


class AllQuizFragment : Fragment() {
    // Get item in view
    private lateinit var toolBarTitle: TextView
    private lateinit var toolBarBack: ImageButton
    private lateinit var toolBarAddQuiz: ImageButton

    private lateinit var recycleViewSUAP: RecyclerView
    private lateinit var recycleViewOD: RecyclerView
    private lateinit var recycleViewINC: RecyclerView

    private lateinit var layoutMyQuiz: ConstraintLayout

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
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        toolBarBack = view.findViewById(R.id.imageButtonBack)
        toolBarAddQuiz = view.findViewById(R.id.imageButtonAddQuizz)

        recycleViewINC = view.findViewById(R.id.recycler_view_inc)
        recycleViewOD = view.findViewById(R.id.recycler_view_od)
        recycleViewSUAP = view.findViewById(R.id.recycler_view_suap)

        layoutMyQuiz = view.findViewById(R.id.my_quiz_page_layout)


        configureRecyclerView(DataQuiz.listQuizINC, recycleViewINC)
        configureRecyclerView(DataQuiz.listQuizOD, recycleViewOD)
        configureRecyclerView(DataQuiz.listQuizSAP, recycleViewSUAP)

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

        layoutMyQuiz.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser != null) {
                if (DataQuiz.listQuizUser.isNotEmpty()) {
                    loadFragment(AllQuizUserFragment())

                } else {
                    Toast.makeText(context, "La liste est vide attend !", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Vous devez être connectez !", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun configureRecyclerView(list: List<Quiz>, recyclerView: RecyclerView) {
        val adapter = context?.let { QuizAdapter(list, it) }
        adapter.also { recyclerView.adapter = it }
        val horizontalLayout = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.layoutManager = horizontalLayout
    }


    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main_act, fragment)
            .setReorderingAllowed(true)
            .addToBackStack("detail").commit()
    }
}