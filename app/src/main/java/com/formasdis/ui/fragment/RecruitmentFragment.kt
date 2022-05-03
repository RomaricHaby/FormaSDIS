package com.formasdis.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.formasdis.R
import com.formasdis.ui.fragment.recruitment.ProfessionalFragment
import com.formasdis.ui.fragment.recruitment.VolunteerFragment
import com.formasdis.ui.fragment.recruitment.YoungFragment
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class RecruitmentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_recruitment, container, false)
        val toolBarTitle = view.findViewById<TextView>(R.id.titleToolBar)
        val toolBarBack = view.findViewById<ImageButton>(R.id.imageButtonBack)
        val imageYoung = view.findViewById<ImageButton>(R.id.imageButtonYoung)
        val imageProfessional = view.findViewById<ImageButton>(R.id.imageButtonProfessional)
        val imageVolunteer = view.findViewById<ImageButton>(R.id.imageButtonVolunteer)

        toolBarBack.visibility= View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Devenir sapeur-pompier"
        toolBarTitle.textSize = 20F

        imageYoung.setOnClickListener {
            loadFragment(YoungFragment())
        }

        imageProfessional.setOnClickListener {
            loadFragment(ProfessionalFragment())
        }

        imageVolunteer.setOnClickListener {
            loadFragment((VolunteerFragment()))
        }

        Picasso.get()
            .load("https://remeng.rosselcdn.net/sites/default/files/dpistyles_v2/ena_16_9_extra_big/2021/07/20/node_277083/12347467/public/2021/07/20/B9727751521Z.1_20210720110621_000%2BGN4IIQBQT.1-0.jpg?itok=i70GaQBG1626771987")
            .resize(imageYoung.width,450)
            .into(imageYoung)

        Picasso.get()
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Pompiers_IMG_2761.JPG/520px-Pompiers_IMG_2761.JPG")
            .resize(imageProfessional.width,450)
            .into(imageProfessional)

        Picasso.get()
            .load("https://demarchesadministratives.fr/images/demarches/957/comment-devenir-pompier-volontaire-1.jpg")
            .resize(imageVolunteer.width,450)
            .into(imageVolunteer)

        toolBarBack.setOnClickListener {
            loadFragment(HomeFragment())
        }
        return view
    }

    //Management fragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setReorderingAllowed(true)
            .addToBackStack("detail").commit()
    }
}

