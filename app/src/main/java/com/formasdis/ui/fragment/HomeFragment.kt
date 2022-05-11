package com.formasdis.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.formasdis.R
import com.formasdis.pdf.MainActivityPdf
import com.formasdis.ui.activity.MainActivity
import com.formasdis.ui.activity.account.AccountActivity
import com.formasdis.ui.activity.account.LoginActivity
import com.formasdis.ui.fragment.user.ShowScoreFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val toolBarTitle = view.findViewById<TextView>(R.id.titleToolBar)
        val imageAccount = view.findViewById<ImageButton>(R.id.imageAccount)
        val score = view.findViewById<ImageButton>(R.id.imageTrophy)

        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Forma SDIS"

        if (activity is MainActivity) {
            (activity as MainActivity).resetNavBar()
        }

        // Initialize Firebase Auth
        auth = Firebase.auth

        score.setOnClickListener {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                loadFragment(ShowScoreFragment())
            } else {
                val intent = Intent(activity, LoginActivity::class.java)
                activity?.startActivity(intent)
            }
        }

        imageAccount.setOnClickListener {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                val intent = Intent(activity, AccountActivity::class.java)
                activity?.startActivity(intent)
            } else {
                val intent = Intent(activity, LoginActivity::class.java)
                activity?.startActivity(intent)
            }
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