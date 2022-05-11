package com.formasdis.ui.adapter.lesson

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Lesson
import com.formasdis.ui.fragment.education.WebViewFragment
import com.formasdis.pdf.PdfViewerActivity

class LessonAdapter(
    val context : Context,
    private val listLesson: List<Lesson>,
    val parentFragmentManager: FragmentManager
) : RecyclerView.Adapter<LessonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_lesson, parent, false)
        return LessonViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val item = listLesson[position]

        holder.itemView.setOnClickListener {
            context.startActivity(
                PdfViewerActivity.launchPdfFromPath(
                    context,
                    "${item.name}.pdf",
                    item.name,
                    "assets",
                    enableDownload = false,
                    fromAssets = true,
                )
            )
        }

        holder.updateLesson(item)
    }

    override fun getItemCount(): Int {
        return listLesson.size
    }
}