package com.formasdis.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.ui.activity.MainActivity
import com.formasdis.ui.fragment.account.LoginFragment
import com.google.firebase.FirebaseApp

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val toolBarTitle = view.findViewById<TextView>(R.id.titleToolBar)
        val imageAccount = view.findViewById<ImageButton>(R.id.imageAccount)
        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Forma SDIS"

        if (activity is MainActivity) {
            (activity as MainActivity).resetNavBar()
        }

        imageAccount.setOnClickListener {
            //if (FirebaseA)
            loadFragment(LoginFragment())
        }

        return view
    }

    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main_act, fragment)
            .setReorderingAllowed(true)
            .addToBackStack("detail").commit()
    }
}