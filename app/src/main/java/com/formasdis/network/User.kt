package com.formasdis.network

import com.formasdis.model.CurrentQuiz
import com.formasdis.model.Score

object User {
    var currentQuiz = CurrentQuiz()
    var uID: String = ""

    val listIdQuiz = ArrayList<Long>()
    val listScore = ArrayList<Score>()

}