package com.formasdis.ui.fragment

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.formasdis.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val toolBarTitle = view.findViewById<TextView>(R.id.titleToolBar)
        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Forma SDIS"

        return view
    }
}