package com.formasdis.network

import android.util.Log
import com.formasdis.model.Quiz
import com.formasdis.model.Score
import com.formasdis.network.ClientFirebase.myRef
import com.google.firebase.auth.FirebaseAuth

object DataUser {

    fun writeNewUser() {
        myRef.child("user").child(User.uID).setValue(User)
    }

    fun loadUser() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            myRef.child("user").child(User.uID).get().addOnSuccessListener {
                for (dataQuiz in it.children) {
                    when (dataQuiz.key.toString()) {
                        "myQuiz" -> {
                            User.listIdQuiz.clear()
                            DataQuiz.listQuizUser.clear()
                            for (idQuiz in dataQuiz.children) {
                                User.listIdQuiz.add(idQuiz.value.toString().toLong())
                            }
                        }
                        /*"currentQuiz" -> {
                            for (idCurrentQuiz in dataQuiz.children) {

                                for (currentQuizData in idCurrentQuiz.children) {
                                    var idQuiz = ""
                                    var finish = true

                                    when (currentQuizData.key) {
                                      /*  "currentQuestion" -> {
                                            for (currentQuestion in currentQuizData.children) {
                                                var nameQuestion = ""
                                                var urlImage = ""
                                                var type = 0

                                                for (dataCurrentQuestion in currentQuestion.children) {
                                                    when (dataCurrentQuestion.key.toString()) {
                                                        "listAnswer" -> {
                                                            for (answerId in dataCurrentQuestion.children) {
                                                                var answer = ""
                                                                var correct = false

                                                                for (dataAnswer in answerId.children) {
                                                                    when (dataAnswer.key.toString()) {
                                                                        "answer" -> answer =
                                                                            dataAnswer.value.toString()
                                                                        "correct" -> correct =
                                                                            dataAnswer.value.toString()
                                                                                .toBoolean()
                                                                    }
                                                                }
                                                                User.currentQuiz.currentQuestion?.listAnswer?.add(
                                                                    Answer(
                                                                        answer,
                                                                        correct
                                                                    )
                                                                )
                                                            }
                                                        }
                                                        "nameQuestion" -> nameQuestion =
                                                            dataCurrentQuestion.value.toString()
                                                        "type" -> type =
                                                            dataCurrentQuestion.value.toString()
                                                                .toInt()

                                                        "urlImage" -> urlImage =
                                                            dataCurrentQuestion.value.toString()

                                                    }
                                                }
                                                User.currentQuiz.currentQuestion?.nameQuestion =
                                                    nameQuestion
                                                User.currentQuiz.currentQuestion?.type = type
                                                User.currentQuiz.currentQuestion?.urlImage =
                                                    urlImage
                                            }
                                        }
*/
                                        "finish" -> {
                                            finish = currentQuizData.value.toString().toBoolean()
                                        }

                                        "idQuiz" -> {
                                            idQuiz = currentQuizData.value.toString()
                                        }
                                    }
                                    User.currentQuiz.isFinish = finish
                                    User.currentQuiz.idQuiz = idQuiz

                                    DataQuiz.getCurrentQuizById(User.currentQuiz.idQuiz.toLong())
                                }

                            }
                        }*/
                        "myScore" -> {
                            for (idScore in dataQuiz.children) {
                                var nomQuiz = ""
                                var idQuiz = 0L
                                var score = ""
                                for (scoreData in idScore.children) {
                                    when (scoreData.key) {
                                        "nomQuiz" -> {
                                            nomQuiz = scoreData.value.toString()
                                        }

                                        "idQuiz" -> {
                                            idQuiz = scoreData.value.toString().toLong()
                                        }

                                        "score" -> {
                                            score = scoreData.value.toString()
                                        }
                                    }
                                }
                                User.listScore.add(0, Score(nomQuiz, idQuiz, score))
                            }
                        }
                    }
                }
                for (idQuiz in User.listIdQuiz) {
                    DataQuiz.getQuizById(idQuiz)
                }
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }
    }

    fun updateCurrentQuiz() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            myRef.child("user").child(User.uID).child("currentQuiz").setValue(User.currentQuiz)
        }
    }

    fun addNewQuiz() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            myRef.child("user").child(User.uID).child("myQuiz").removeValue()
            myRef.child("user").child(User.uID).child("myQuiz").setValue(User.listIdQuiz)
        }
    }

    fun deleteQuiz(quiz: Quiz) {
        //myRef.child("user").child(User.uID).child("myQuiz").removeValue()
    }

    fun addNewScore() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            myRef.child("user").child(User.uID).child("myScore").removeValue()
            myRef.child("user").child(User.uID).child("myScore").setValue(User.listScore)
        }
    }

    fun clearData() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            myRef.child("user").child(User.uID).removeValue()
            FirebaseAuth.getInstance().signOut()
        }
    }

}
