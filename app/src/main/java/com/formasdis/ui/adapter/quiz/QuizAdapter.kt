package com.formasdis.ui.adapter.quiz

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Quiz
import com.formasdis.network.DataUser
import com.formasdis.network.User
import com.formasdis.ui.activity.MainActivity

class QuizAdapter(private val listQuiz: List<Quiz>, val context: Context) :
    RecyclerView.Adapter<QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_quiz, parent, false)
        return QuizViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val items = listQuiz[position]
        holder.updateQuiz(items)


        holder.itemView.setOnClickListener {
            // Init the current quiz
            User.currentQuiz.quiz = items
            User.currentQuiz.currentQuestion = items.listQuestions[0]

            DataUser.updateCurrentQuiz()

            val activity = holder.itemView.context as Activity
            if (activity is MainActivity) {
                (activity).swipeActivity()
            }
        }
    }

    override fun getItemCount(): Int {
        return listQuiz.size
    }
}