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

        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }

    }

    fun clearData() {
        ClientFirebase.myRef.child("user").child(User.uID).removeValue()
    }

}