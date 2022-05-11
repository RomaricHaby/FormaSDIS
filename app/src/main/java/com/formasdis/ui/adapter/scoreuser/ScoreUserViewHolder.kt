package com.formasdis.ui.adapter.scoreuser

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Score

class ScoreUserViewHolder(itemView: View, val context: Context) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var titleQuiz: TextView
    private lateinit var shareCode: TextView
    private lateinit var scoreTextView: TextView

    private fun initUI() {
        titleQuiz = itemView.findViewById(R.id.titleQuizScoreUser)
        scoreTextView = itemView.findViewById(R.id.scoreQuizScoreUser)
        shareCode = itemView.findViewById(R.id.idQuizScoreUser)
    }

    fun updateScore(score: Score) {
        initUI()
        titleQuiz.text = score.nomQuiz
        scoreTextView.text = score.score
        shareCode.text = score.idQuiz.toString()
    }
}