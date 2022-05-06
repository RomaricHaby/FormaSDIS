package com.formasdis.ui.fragment.quiz.show_quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.network.User
import com.formasdis.ui.adapter.result.ResultAdapter

class ShowResultQuiz : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var result: TextView

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
        recyclerView = view.findViewById(R.id.recyclerViewResult)
        result = view.findViewById(R.id.resultQuizFragment)

        var nbrGoodAnswer = 0

        for (answer in User.currentQuiz.correctAnswer){
            if (answer){
                nbrGoodAnswer++
            }
        }

        result.text = "Note ${nbrGoodAnswer}/${User.currentQuiz.quiz?.nbrQuestion}"
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val adapter = context?.let { ResultAdapter(it) }
        adapter.also { recyclerView.adapter = it }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}