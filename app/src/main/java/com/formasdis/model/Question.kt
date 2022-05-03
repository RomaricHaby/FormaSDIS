package com.formasdis.model

data class Question(
    val nameQuestion: String,
    val type: Int,
    val listAnswer: List<Answer>
)
