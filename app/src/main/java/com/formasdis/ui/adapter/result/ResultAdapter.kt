package com.formasdis.ui.adapter.result

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.network.User
import com.formasdis.ui.activity.CurrentQuizActivity
import com.formasdis.ui.activity.MainActivity
import com.formasdis.ui.fragment.quiz.add_quiz.AddQuestionFragment
import com.formasdis.ui.fragment.quiz.show_quiz.ShowQuestion

class ResultAdapter(val context: Context, val parentFragmentManager: FragmentManager) :
    RecyclerView.Adapter<ResultViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_result, parent, false)
        return ResultViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val items = User.currentQuiz.quiz?.listQuestions?.get(position)
        if (items != null) {
            holder.updateResult(items, position)
        }


        holder.itemView.setOnClickListener {
            User.currentQuiz.currentQuestion = User.currentQuiz.quiz?.listQuestions?.get(position)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_current_quiz_act, ShowQuestion(true))
                .setReorderingAllowed(true)
                .addToBackStack("detail").commit()

        }
    }

    override fun getItemCount(): Int {
        return User.currentQuiz.quiz?.listQuestions?.size!!
    }
}