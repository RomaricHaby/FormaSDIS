package com.formasdis.network

import android.util.Log
import com.formasdis.model.Quiz
import com.formasdis.network.ClientFirebase.myRef
import com.google.firebase.auth.FirebaseAuth

object DataUser {

    fun writeNewUser() {
        myRef.child("user").child(User.uID).setValue(User)
    }

    fun loadUser() {

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
                }
            }

            for (idQuiz in User.listIdQuiz){
                DataQuiz.getQuizById(idQuiz)
            }
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }

    fun updateCurrentQuiz() {
        myRef.child("user").child(User.uID).child("currentQuiz").setValue(User.currentQuiz)
    }

    fun addNewQuiz() {
        myRef.child("user").child(User.uID).child("myQuiz").removeValue()
        myRef.child("user").child(User.uID).child("myQuiz").setValue(User.listIdQuiz)
    }

    fun deleteQuiz(quiz: Quiz) {
        //myRef.child("user").child(User.uID).child("myQuiz").removeValue()
    }


    fun clearData() {
        myRef.child("user").child(User.uID).removeValue()
        FirebaseAuth.getInstance().signOut()
    }

}