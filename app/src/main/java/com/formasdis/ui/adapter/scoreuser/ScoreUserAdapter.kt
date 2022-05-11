package com.formasdis.ui.adapter.scoreuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.network.User

class ScoreUserAdapter :
    RecyclerView.Adapter<ScoreUserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreUserViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_score_user, parent, false)
        return ScoreUserViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ScoreUserViewHolder, position: Int) {
        val items = User.listScore[position]
        holder.updateScore(items)
    }

    override fun getItemCount(): Int {
        return User.listScore.size
    }
}