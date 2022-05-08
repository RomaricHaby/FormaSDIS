package com.formasdis.ui.adapter.lesson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Lesson
import com.formasdis.ui.fragment.education.WebViewFragment

class LessonAdapter(
    private val listLesson: List<Lesson>,
    val parentFragmentManager: FragmentManager
) : RecyclerView.Adapter<LessonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_lesson, parent, false)
        return LessonViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val items = listLesson[position]

        holder.itemView.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_main_act, WebViewFragment(items.urlPdf, true))
                .setReorderingAllowed(true)
                .addToBackStack("detail").commit()
        }


        holder.updateLesson(items)
    }

    override fun getItemCount(): Int {
        return listLesson.size
    }
}