package com.formasdis.model

data class CurrentQuiz(
    var quiz: Quiz?,
    var currentQuestion: Question?,
    val correctAnswer: ArrayList<Boolean>,
    var isFinish: Boolean
) {
    constructor() : this(null, null, ArrayList<Boolean>(), true)
}