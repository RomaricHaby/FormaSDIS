package com.formasdis.ui.fragment.recruitment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.RoundedCornersTransformation
import com.formasdis.R
import com.formasdis.ui.fragment.HomeFragment

class VolunteerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_volunteer, container, false)
        val toolBarHome = view.findViewById<ImageButton>(R.id.imageButtonHome)
        val toolBarTitle = view.findViewById<TextView>(R.id.titleToolBar)
        val toolBarBack = view.findViewById<ImageButton>(R.id.imageButtonBack)

        val image = view.findViewById<ImageView>(R.id.imageViewVolunteer)

        // ToolBar
        toolBarHome.visibility = View.VISIBLE
        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "DEVENIR SAPEUR-POMPIER VOLONTAIRE"
        toolBarTitle.textSize = 14F

        toolBarHome.setOnClickListener {
            loadFragment(HomeFragment())
        }
        toolBarBack.setOnClickListener {
            loadFragment(RecruitmentFragment())
        }


        image.load("https://www.pompiers.fr/sites/default/files/content/text/picture/visuel-tweet_volontariat-v2ok-800x650.jpg") {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(RoundedCornersTransformation(20f, 20f, 20f, 20f))
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
