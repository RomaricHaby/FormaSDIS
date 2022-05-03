package com.formasdis.network

import com.formasdis.model.Answer
import com.formasdis.model.Question
import com.formasdis.model.Quiz

object DataQuiz {
    val listQuiz = ArrayList<Quiz>()


    fun getAllQuiz() {
        ClientFirebase.myRef.child("quiz").get().addOnSuccessListener {
            for (idQuiz in it.children) {
                var name = "null"
                var nbrQuestion = 0
                var type = ""
                val listQuestions = ArrayList<Question>()

                val id: Int = idQuiz.key.toString().toInt()
                for (dataQuiz in it.child(idQuiz.key.toString()).children) {
                    when (dataQuiz.key.toString()) {
                        "name" -> name = dataQuiz.value.toString()
                        "nbrQuestion" -> nbrQuestion = dataQuiz.value.toString().toInt()
                        "type" -> type = dataQuiz.value.toString()
                        "questions" -> {
                            for (questionsId in dataQuiz.children) {
                                var typeQuestion = 0
                                var question = ""
                                val listAnswer = ArrayList<Answer>()

                                for (dataQuestion in questionsId.children) {
                                    when (dataQuestion.key.toString()) {
                                        "type" -> typeQuestion = dataQuestion.value.toString().toInt()
                                        "question" -> question = dataQuestion.value.toString()
                                        "answer" -> {
                                            for (answerId in dataQuestion.children) {
                                                var answer = ""
                                                var correct = false

                                                for (dataAnswer in answerId.children) {
                                                    when (dataAnswer.key.toString()) {
                                                        "answer" -> answer = dataAnswer.value.toString()
                                                        "correct" -> correct = dataAnswer.value.toString().toBoolean()
                                                    }
                                                }
                                                listAnswer.add(Answer(answer, correct))
                                            }
                                        }
                                    }
                                }
                                listQuestions.add(Question(question, typeQuestion, listAnswer))
                            }
                        }
                    }
                }
                listQuiz.add(Quiz(id, name, nbrQuestion, type, listQuestions))
            }
        }
    }
}

