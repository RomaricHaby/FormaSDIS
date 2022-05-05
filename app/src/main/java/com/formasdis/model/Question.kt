package com.formasdis.model

data class Question(
    var nameQuestion: String,
    var type: Int,
    val listAnswer: ArrayList<Answer>
)
