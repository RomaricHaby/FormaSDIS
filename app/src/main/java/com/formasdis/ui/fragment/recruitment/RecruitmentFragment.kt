package com.formasdis.ui.fragment.recruitment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.RoundedCornersTransformation
import com.formasdis.R
import com.formasdis.ui.fragment.HomeFragment

class RecruitmentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // View
        val view = inflater.inflate(R.layout.fragment_recruitment, container, false)

        // Get item in view
        val toolBarTitle = view.findViewById<TextView>(R.id.titleToolBar)
        val toolBarBack = view.findViewById<ImageButton>(R.id.imageButtonBack)

        val imageYoung = view.findViewById<ImageButton>(R.id.imageButtonYoung)
        val imageProfessional = view.findViewById<ImageButton>(R.id.imageButtonProfessional)
        val imageVolunteer = view.findViewById<ImageButton>(R.id.imageButtonVolunteer)


        // ToolBar

        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Devenir sapeur-pompier"
        toolBarTitle.textSize = 20F

        toolBarBack.setOnClickListener {
            loadFragment(HomeFragment())
        }


        // Volunteer
        imageVolunteer.load("https://demarchesadministratives.fr/images/demarches/957/comment-devenir-pompier-volontaire-1.jpg") {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(RoundedCornersTransformation(20f, 20f, 20f, 20f))
            size(810, 465)
        }

        imageVolunteer.setOnClickListener {
            loadFragment((VolunteerFragment()))
        }


        // Professional
        imageProfessional.load("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Pompiers_IMG_2761.JPG/520px-Pompiers_IMG_2761.JPG") {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(RoundedCornersTransformation(20f, 20f, 20f, 20f))
            size(810, 465)
        }

        imageProfessional.setOnClickListener {
            loadFragment(ProfessionalFragment())
        }

        // Young
        imageYoung.load("https://remeng.rosselcdn.net/sites/default/files/dpistyles_v2/ena_16_9_extra_big/2021/07/20/node_277083/12347467/public/2021/07/20/B9727751521Z.1_20210720110621_000%2BGN4IIQBQT.1-0.jpg?itok=i70GaQBG1626771987") {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(RoundedCornersTransformation(20f, 20f, 20f, 20f))
            size(810, 465)
        }

        imageYoung.setOnClickListener {
            loadFragment(YoungFragment())
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

