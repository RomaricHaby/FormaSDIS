package com.formasdis.ui.adapter.result

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Question
import com.formasdis.network.User

class ResultViewHolder(itemView: View, val context: Context) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var titleQuestion: TextView
    private lateinit var check: ImageView

    private fun initUI() {
        titleQuestion = itemView.findViewById(R.id.titleQuizScoreUser)
        check = itemView.findViewById(R.id.image_result_view_holder)
    }


    fun updateResult(question: Question, position: Int) {
        initUI()
        titleQuestion.text = question.nameQuestion


        if (User.currentQuiz.correctAnswer[position]) {
            check.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_done_green
                )
            )
        }
    }
}