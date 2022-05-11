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
                            for (idQuiz in dataQuiz.children) {
                                User.listIdQuiz.add(idQuiz.value.toString().toLong())
                            }
                        }
                        "currentQuiz" -> {

                        }
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
