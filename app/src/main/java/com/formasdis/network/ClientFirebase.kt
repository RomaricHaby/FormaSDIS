package com.formasdis.network

import com.google.firebase.database.FirebaseDatabase

object ClientFirebase {
    private val database =
        FirebaseDatabase.getInstance("https://formasdis-212a6-default-rtdb.europe-west1.firebasedatabase.app/")
    val myRef = database.reference
}