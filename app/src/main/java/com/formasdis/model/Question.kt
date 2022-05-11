package com.formasdis.model

data class Question(
    var nameQuestion: String,
    var type: Int,
    var urlImage: String,
    val listAnswer: ArrayList<Answer>
)
