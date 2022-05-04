import com.formasdis.model.Lesson
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.ui.fragment.education.LessonViewHolder

class LessonAdapter(private val listLesson: List<Lesson>) : RecyclerView.Adapter<LessonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_lesson, parent, false)
        return LessonViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val items = listLesson[position]
        holder.updateLesson(items)
    }

    override fun getItemCount(): Int {
        return listLesson.size
    }
}