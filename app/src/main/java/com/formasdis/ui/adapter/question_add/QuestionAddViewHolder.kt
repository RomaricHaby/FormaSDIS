package com.formasdis.ui.adapter.question_add

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Quiz

class QuestionAddViewHolder(itemView: View, val context: Context) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var titleQuestionAdd: TextView
    private lateinit var image: ImageView

    private fun initUI() {
        titleQuestionAdd = itemView.findViewById(R.id.title_question_add_view_holder)
        image = itemView.findViewById(R.id.image_question_add_view_holder)
    }

    fun updateQuestionAdd(int: Int, quiz: Quiz, position: Int) {
        initUI()
        if (quiz.listQuestions[position].nameQuestion.isNotBlank()) {
            image.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_done
                )
            )
        }

        titleQuestionAdd.text = "Question $int"
    }
}