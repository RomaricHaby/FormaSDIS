package com.formasdis.ui.adapter.result

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.CurrentQuiz
import com.formasdis.model.Question
import com.formasdis.network.User

class ResultAdapter(val context: Context) :
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

        }
    }

    override fun getItemCount(): Int {
        return User.currentQuiz.quiz?.listQuestions?.size!!
    }
}