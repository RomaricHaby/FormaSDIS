package com.formasdis.ui.fragment.user

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
import com.formasdis.ui.adapter.scoreuser.ScoreUserAdapter
import com.formasdis.ui.fragment.HomeFragment

class ShowScoreFragment : Fragment() {

    private lateinit var recyclerViewShowScore: RecyclerView
    private lateinit var toolBarTitle: TextView
    private lateinit var toolBarBack: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_score, container, false)

        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        recyclerViewShowScore = view.findViewById(R.id.recyclerVIewShowScore)

        // ToolBar
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        toolBarBack = view.findViewById(R.id.imageButtonBack)

        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Mes scores"
        toolBarTitle.textSize = 20F

        toolBarBack.setOnClickListener {
            loadFragment(HomeFragment())
        }

        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val adapter = ScoreUserAdapter()
        adapter.also { recyclerViewShowScore.adapter = it }
        recyclerViewShowScore.layoutManager = LinearLayoutManager(context)
    }

    // Management fragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main_act, fragment)
            .setReorderingAllowed(true)
            .addToBackStack("detail").commit()
    }
}