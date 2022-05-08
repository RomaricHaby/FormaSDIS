package com.formasdis.ui.adapter.question_add

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Quiz
import com.formasdis.ui.fragment.quiz.add_quiz.AddQuestionFragment

class QuestionAddAdapter(
    private val listInt: List<Int>,
    private val parentFragmentManager: FragmentManager,
    private val quiz: Quiz,
    private val context: Context
) : RecyclerView.Adapter<QuestionAddViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAddViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_question_add, parent, false)
        return QuestionAddViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: QuestionAddViewHolder, position: Int) {
        val number = listInt[position]
        holder.updateQuestionAdd(number, quiz, position)

        holder.itemView.setOnClickListener {
            if (quiz.listQuestions[position].nameQuestion.isBlank()) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_main_act, AddQuestionFragment(quiz, position))
                    .setReorderingAllowed(true)
                    .addToBackStack("detail").commit()
            } else {
                Toast.makeText(context, "Cette question est déja complète", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun getItemCount(): Int {
        return listInt.size
    }
}