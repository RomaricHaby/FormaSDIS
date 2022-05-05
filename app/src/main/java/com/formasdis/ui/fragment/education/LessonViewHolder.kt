package com.formasdis.ui.fragment.education
import coil.load
import coil.transform.RoundedCornersTransformation
import com.formasdis.model.Lesson
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R

class LessonViewHolder(itemView: View, val context: Context) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var nameLesson: TextView
    private lateinit var imageLesson: ImageView

    private fun initUI() {
        nameLesson = itemView.findViewById(R.id.nameLesson)
        imageLesson = itemView.findViewById(R.id.imageLesson)
    }


    fun updateLesson(lesson: Lesson) {
        initUI()

        nameLesson.text = lesson.name
        imageLesson.load(lesson.urlImage) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(RoundedCornersTransformation(20f,20f,20f,20f))
            size(400,400)
        }
    }
}