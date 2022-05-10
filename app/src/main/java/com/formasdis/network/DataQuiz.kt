package com.formasdis.network

import com.formasdis.model.Answer
import com.formasdis.model.Question
import com.formasdis.model.Quiz

object DataQuiz {
    val listQuizApp = ArrayList<Quiz>()

    val listQuizUser = ArrayList<Quiz>()

    val listQuizOD = ArrayList<Quiz>()
    val listQuizSAP = ArrayList<Quiz>()
    val listQuizINC = ArrayList<Quiz>()


    private fun filterQuiz() {
        for (quiz in listQuizApp) {
            when (quiz.type.lowercase()) {
                "od" -> listQuizOD.add(quiz)
                "inc" -> listQuizINC.add(quiz)
                "sap" -> listQuizSAP.add(quiz)
            }
        }
    }

    fun addQuiz(quiz: Quiz) {
        ClientFirebase.myRef.child("quiz").child(quiz.id.toString()).setValue(quiz)
    }

    fun getAllQuiz() {
        ClientFirebase.myRef.child("quizApp").get().addOnSuccessListener {
            for (idQuiz in it.children) {
                var name = "null"
                var nbrQuestion = 0
                var type = ""
                val listQuestions = ArrayList<Question>()

                val id: Long = idQuiz.key.toString().toLong()
                for (dataQuiz in it.child(idQuiz.key.toString()).children) {
                    when (dataQuiz.key.toString()) {
                        "name" -> name = dataQuiz.value.toString()
                        "nbrQuestion" -> nbrQuestion = dataQuiz.value.toString().toInt()
                        "type" -> type = dataQuiz.value.toString()
                        "listQuestions" -> {
                            for (questionsId in dataQuiz.children) {
                                var typeQuestion = 0
                                var question = ""
                                var url = ""
                                val listAnswer = ArrayList<Answer>()

                                for (dataQuestion in questionsId.children) {
                                    when (dataQuestion.key.toString()) {
                                        "type" -> typeQuestion =
                                            dataQuestion.value.toString().toInt()
                                        "nameQuestion" -> question = dataQuestion.value.toString()
                                        "urlImage" -> url = dataQuestion.value.toString()
                                        "listAnswer" -> {
                                            for (answerId in dataQuestion.children) {
                                                var answer = ""
                                                var correct = false

                                                for (dataAnswer in answerId.children) {
                                                    when (dataAnswer.key.toString()) {
                                                        "answer" -> answer =
                                                            dataAnswer.value.toString()
                                                        "correct" -> correct =
                                                            dataAnswer.value.toString().toBoolean()
                                                    }
                                                }
                                                listAnswer.add(Answer(answer, correct))
                                            }
                                        }
                                    }
                                }
                                listQuestions.add(Question(question, typeQuestion, url, listAnswer))
                            }
                        }
                    }
                }
                listQuizApp.add(Quiz(id, name, nbrQuestion, type, listQuestions))
            }

            filterQuiz()
        }
    }

    fun getQuizById(id: Int) {
        ClientFirebase.myRef.child("quiz").child(id.toString()).get().addOnSuccessListener {
            var name = "null"
            var nbrQuestion = 0
            var type = ""
            val listQuestions = ArrayList<Question>()

            for (dataQuiz in it.children) {
                when (dataQuiz.key.toString()) {
                    "name" -> name = dataQuiz.value.toString()
                    "nbrQuestion" -> nbrQuestion = dataQuiz.value.toString().toInt()
                    "type" -> type = dataQuiz.value.toString()
                    "listQuestions" -> {
                        for (questionsId in dataQuiz.children) {
                            var typeQuestion = 0
                            var question = ""
                            var url = ""
                            val listAnswer = ArrayList<Answer>()

                            for (dataQuestion in questionsId.children) {
                                when (dataQuestion.key.toString()) {
                                    "type" -> typeQuestion =
                                        dataQuestion.value.toString().toInt()
                                    "nameQuestion" -> question = dataQuestion.value.toString()
                                    "urlImage" -> url = dataQuestion.value.toString()
                                    "listAnswer" -> {
                                        for (answerId in dataQuestion.children) {
                                            var answer = ""
                                            var correct = false

                                            for (dataAnswer in answerId.children) {
                                                when (dataAnswer.key.toString()) {
                                                    "answer" -> answer =
                                                        dataAnswer.value.toString()
                                                    "correct" -> correct =
                                                        dataAnswer.value.toString().toBoolean()
                                                }
                                            }
                                            listAnswer.add(Answer(answer, correct))
                                        }
                                    }
                                }
                            }
                            listQuestions.add(Question(question, typeQuestion, url, listAnswer))
                        }
                    }
                }
            }
            //  listQuiz.add(Quiz(id, name, nbrQuestion, type, listQuestions))
        }
    }
}

