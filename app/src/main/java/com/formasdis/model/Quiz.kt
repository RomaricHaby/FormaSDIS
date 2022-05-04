package com.formasdis.model

data class Quiz(
    val id: Int,
    val name: String,
    val nbrQuestion: Int,
    val type: String,
    val listQuestions: List<Question>,
    val shareCode : String
)
