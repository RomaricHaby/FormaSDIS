package com.formasdis.model

data class Quiz(
    val id: Long,
    val name: String,
    val nbrQuestion: Int,
    val type: String,
    val listQuestions: ArrayList<Question>,
)
