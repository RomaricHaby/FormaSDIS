package com.formasdis.ui.adapter.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Quiz

class QuizAdapter(private val listQuiz: List<Quiz>) : RecyclerView.Adapter<QuizViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_quiz, parent, false)
        return QuizViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val items = listQuiz[position]
        holder.updateQuiz(items)
    }

    override fun getItemCount(): Int {
        return listQuiz.size
    }
}