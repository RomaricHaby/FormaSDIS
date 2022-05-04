package com.formasdis.ui.adapter.question_add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R

class QuestionAddAdapter (private val listInt: List<Int>) : RecyclerView.Adapter<QuestionAddViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAddViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_question_add, parent, false)
        return QuestionAddViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: QuestionAddViewHolder, position: Int) {
        val number = listInt[position]
        holder.updateQuestionAdd(number)
    }

    override fun getItemCount(): Int {
        return listInt.size
    }
}