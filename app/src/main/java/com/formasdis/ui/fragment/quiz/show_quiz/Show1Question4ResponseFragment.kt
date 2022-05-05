package com.formasdis.ui.fragment.quiz.show_quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.formasdis.R

class Show1Question4ResponseFragment() : Fragment() {

    private lateinit var questionName: TextView

    private lateinit var response1TextView: TextView
    private lateinit var response2TextView: TextView
    private lateinit var response3TextView: TextView
    private lateinit var response4TextView: TextView

    private lateinit var response1Button: Button
    private lateinit var response2Button: Button
    private lateinit var response3Button: Button
    private lateinit var response4Button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_1question_4response_fragment, container, false)


        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        questionName = view.findViewById(R.id.questionName1Question4Response)

        response1TextView = view.findViewById(R.id.response1TextView)
        response2TextView = view.findViewById(R.id.response2TextView)
        response3TextView = view.findViewById(R.id.response3TextView)
        response4TextView = view.findViewById(R.id.response4TextView)

        response1Button = view.findViewById(R.id.response1Button)
        response2Button = view.findViewById(R.id.response2Button)
        response3Button = view.findViewById(R.id.response3Button)
        response4Button = view.findViewById(R.id.response4Button)

        
    }
}