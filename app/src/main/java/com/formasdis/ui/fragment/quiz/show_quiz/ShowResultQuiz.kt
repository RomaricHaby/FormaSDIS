package com.formasdis.ui.fragment.quiz.show_quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Score
import com.formasdis.network.DataUser
import com.formasdis.network.User
import com.formasdis.ui.activity.MainActivity
import com.formasdis.ui.adapter.result.ResultAdapter

class ShowResultQuiz : Fragment() {
    private lateinit var toolBarTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var result: TextView
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_result_quiz, container, false)

        initUI(view)

        return view
    }

    private fun initUI(view: View) {

        toolBarTitle = view.findViewById(R.id.titleToolBar)
        // ToolBar
        toolBarTitle.visibility = View.VISIBLE

        toolBarTitle.text = "Résultat du quiz"

        recyclerView = view.findViewById(R.id.recyclerViewResult)
        result = view.findViewById(R.id.resultQuizFragment)
        button = view.findViewById(R.id.buttonResultQuiz)

        var nbrGoodAnswer = 0

        for (answer in User.currentQuiz.correctAnswer) {
            if (answer) {
                nbrGoodAnswer++
            }
        }

        result.text = "Note ${nbrGoodAnswer}/${User.currentQuiz.quiz?.nbrQuestion}"
        configureRecyclerView()


        button.setOnClickListener {
            User.currentQuiz.quiz?.id?.let { it1 ->
                Score(
                    User.currentQuiz.quiz?.name ?: "erreur",
                    it1,
                    "${nbrGoodAnswer}/${User.currentQuiz.quiz?.nbrQuestion}"
                )
            }?.let { it2 ->
                User.listScore.add(
                    0,
                    it2
                )
            }

            DataUser.addNewScore()

            User.currentQuiz.quiz = null
            User.currentQuiz.currentQuestion = null
            User.currentQuiz.isFinish = false
            User.currentQuiz.correctAnswer.clear()

            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureRecyclerView() {
        val adapter = context?.let { ResultAdapter(it, parentFragmentManager) }
        adapter.also { recyclerView.adapter = it }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}