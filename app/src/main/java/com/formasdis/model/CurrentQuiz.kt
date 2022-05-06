package com.formasdis.model

data class CurrentQuiz(
    var quiz: Quiz?,
    var currentQuestion: Question?,
    val correctAnswer: ArrayList<Boolean>
    ) {
    constructor() : this(null,null,ArrayList<Boolean>())
}