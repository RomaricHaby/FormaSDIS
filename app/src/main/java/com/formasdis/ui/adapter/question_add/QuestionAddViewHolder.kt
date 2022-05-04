package com.formasdis.ui.adapter.question_add

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R

class QuestionAddViewHolder(itemView: View, val context: Context) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var titleQuestionAdd: TextView

    private fun initUI() {
        titleQuestionAdd = itemView.findViewById(R.id.title_question_add_view_holder)
    }

    fun updateQuestionAdd(int: Int) {
        initUI()
        titleQuestionAdd.text = int.toString()
    }
}