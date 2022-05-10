package com.formasdis.network

import android.util.Log
import com.formasdis.model.CurrentQuiz
import com.formasdis.network.ClientFirebase.myRef

object DataUser {

    fun writeNewUser() {
        myRef.child("user").child(User.uID).setValue(User)
    }

    fun loadUser() {

        myRef.child("user").child(User.uID).get().addOnSuccessListener {
            val test: Any = it.value as CurrentQuiz
            Log.e("firebase", test.toString())
            
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }

    }

}