package com.formasdis.ui.adapter.quiz

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Quiz

class QuizViewHolder(itemView: View, val context: Context) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var titleQuiz: TextView
    private lateinit var nbrQuestions: TextView
    private lateinit var typeQuizIcon: ImageView

    //Init is visibility gone
    private lateinit var shareCode: TextView
    private lateinit var textViewShareCode: TextView

    private fun initUI() {
        titleQuiz = itemView.findViewById(R.id.title_quiz_view_holder)
        nbrQuestions = itemView.findViewById(R.id.nbrquestions_view_holder)
        typeQuizIcon = itemView.findViewById(R.id.type_quiz_ic_view_holder)

        shareCode = itemView.findViewById(R.id.share_code_view_holder)
        textViewShareCode = itemView.findViewById(R.id.text_view_share_code_view_holder)
    }


    fun updateQuiz(quiz: Quiz) {
        initUI()

        /*if (quiz.shareCode.isNotBlank()) {
            shareCode.text = quiz.shareCode
            shareCode.visibility = View.VISIBLE

            textViewShareCode.visibility = View.VISIBLE
        }*/

        titleQuiz.text = quiz.name
        nbrQuestions.text = quiz.nbrQuestion.toString()

        when (quiz.type.lowercase()) {
            "od" -> typeQuizIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_type_od
                )
            )
            "inc" -> typeQuizIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_type_inc
                )
            )
            "sap" -> typeQuizIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_type_suap
                )
            )
        }
    }
}