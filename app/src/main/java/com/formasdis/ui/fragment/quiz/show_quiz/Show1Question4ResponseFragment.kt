package com.formasdis.ui.fragment.quiz.show_quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.formasdis.R
import com.formasdis.model.Question
import com.formasdis.network.User

class Show1Question4ResponseFragment(val question: Question?) : Fragment() {

    private lateinit var toolBarTitle: TextView

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
        toolBarTitle = view.findViewById(R.id.titleToolBar)
        // ToolBar
        toolBarTitle.visibility = View.VISIBLE

        toolBarTitle.text = User.currentQuiz.quiz?.name ?: "erreur"
        toolBarTitle.textSize = 20F


        // Otherwise the correct answer is always first
        question?.listAnswer?.shuffle()

        questionName = view.findViewById(R.id.questionName1Question4Response)

        questionName.text = question?.nameQuestion ?: "Erreur"

        response1TextView = view.findViewById(R.id.response1TextView)
        response1TextView.text = question?.listAnswer?.get(0)?.answer ?: "Erreur"

        response2TextView = view.findViewById(R.id.response2TextView)
        response2TextView.text = question?.listAnswer?.get(1)?.answer ?: "Erreur"

        response3TextView = view.findViewById(R.id.response3TextView)
       response3TextView.text = question?.listAnswer?.get(2)?.answer ?: "Erreur"

        response4TextView = view.findViewById(R.id.response4TextView)
        response4TextView.text = question?.listAnswer?.get(3)?.answer ?: "Erreur"

        response1Button = view.findViewById(R.id.response1Button)
        response2Button = view.findViewById(R.id.response2Button)
        response3Button = view.findViewById(R.id.response3Button)
        response4Button = view.findViewById(R.id.response4Button)

        
    }
}