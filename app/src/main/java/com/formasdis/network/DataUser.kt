package com.formasdis.network

import android.util.Log
import com.formasdis.network.ClientFirebase.myRef
import com.google.firebase.auth.FirebaseAuth

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
        myRef.child("user").child(User.uID).removeValue()
        FirebaseAuth.getInstance().signOut()
    }

}
