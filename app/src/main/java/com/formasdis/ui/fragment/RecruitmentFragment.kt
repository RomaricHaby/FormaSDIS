package com.formasdis.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.formasdis.R
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class RecruitmentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_recruitment, container, false)
        val imageYoung = view.findViewById<ImageButton>(R.id.imageButtonYoung)

        Picasso.get()
            .load("https://remeng.rosselcdn.net/sites/default/files/dpistyles_v2/ena_16_9_extra_big/2021/07/20/node_277083/12347467/public/2021/07/20/B9727751521Z.1_20210720110621_000%2BGN4IIQBQT.1-0.jpg?itok=i70GaQBG1626771987")
            .resize(880,500)
            .into(imageYoung)




        return view
    }


}