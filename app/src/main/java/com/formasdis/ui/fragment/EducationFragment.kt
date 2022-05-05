package com.formasdis.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.formasdis.R
import com.formasdis.ui.fragment.education.RecyclerViewFragment
import com.formasdis.ui.fragment.education.WebViewFragment

class EducationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // View
        val view = inflater.inflate(R.layout.fragment_education, container, false)

        // Get item in view
        val toolBarTitle = view.findViewById<TextView>(R.id.titleToolBar)
        val toolBarBack = view.findViewById<ImageButton>(R.id.imageButtonBack)
        val imageLifeSaving = view.findViewById<ImageButton>(R.id.imageButtonLifeSaving)
        val imageFire = view.findViewById<ImageButton>(R.id.imageButtonFire)
        val imageVariousOperation = view.findViewById<ImageButton>(R.id.imageButtonVariousOperation)


        // ToolBar
        toolBarBack.visibility= View.VISIBLE
        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Thème principal"
        toolBarTitle.textSize = 20F

        toolBarBack.setOnClickListener {
            loadFragment(HomeFragment())
        }

        // Volunteer
        imageLifeSaving.load("https://www.pompiers.fr/sites/default/files/content/text/picture/vsr1.jpg") {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(RoundedCornersTransformation(20f,20f,20f,20f))
            size(810,465)
        }

        imageLifeSaving.setOnClickListener {
            loadFragment((WebViewFragment("https://spf-editions.fr/SUAP_sdis01/SUAP_sdis01/suap.php", false)))
        }


        // Professional
        imageFire.load("https://www.challenges.fr/assets/img/2020/06/03/cover-r4x3w1000-5ed7e5db7c1d8-4eb6a97af6b16645661c49df4167316490af9b98-jpg.jpg") {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(RoundedCornersTransformation(20f,20f,20f,20f))
            size(810,465)
        }

        imageFire.setOnClickListener {
            loadFragment(RecyclerViewFragment("fire"))
        }

        // Young
        imageVariousOperation.load("https://www.leparisien.fr/resizer/s5xMboKY3akFnmdD_gkAMr0svNg=/932x582/cloudfront-eu-central-1.images.arcpublishing.com/leparisien/H6I2CCS54BHP3EPHJCZCZDCFCI.jpg") {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(RoundedCornersTransformation(20f,20f,20f,20f))
            size(810,465)
        }

        imageVariousOperation.setOnClickListener {
            loadFragment(RecyclerViewFragment("various"))
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