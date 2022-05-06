package com.formasdis.ui.fragment.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formasdis.R
import com.formasdis.model.Lesson
import com.formasdis.network.DataLesson
import com.formasdis.ui.adapter.lesson.LessonAdapter
import com.formasdis.ui.fragment.HomeFragment

class ShowListLessonFragment(val type: String) : Fragment() {
    // Get item in view
    private lateinit var toolBarTitle: TextView
    private lateinit var toolBarBack: ImageButton

    private lateinit var recyclerViewLesson: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_list_lesson, container, false)

        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        toolBarBack = view.findViewById(R.id.imageButtonBack)
        recyclerViewLesson = view.findViewById(R.id.recycler_view_lesson)

        if (type == "fire")
            configureRecyclerView(DataLesson.listFire, recyclerViewLesson)
        else
            configureRecyclerView(DataLesson.listVariousOperation, recyclerViewLesson)


        // ToolBar
        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Lesson"
        toolBarTitle.textSize = 20F


        toolBarBack.setOnClickListener {
            loadFragment(HomeFragment())
        }

    }

    private fun configureRecyclerView(list: List<Lesson>, recyclerView: RecyclerView) {
        val adapter = LessonAdapter(list, parentFragmentManager)
        adapter.also { recyclerView.adapter = it }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main_act, fragment)
            .setReorderingAllowed(true)
            .commit()
    }
}