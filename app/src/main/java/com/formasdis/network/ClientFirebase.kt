package com.formasdis.network

import com.google.firebase.database.FirebaseDatabase

object ClientFirebase {
    private val database =
        FirebaseDatabase.getInstance("https://ecommercekotlin-7f974-default-rtdb.europe-west1.firebasedatabase.app/")
    val myRef = database.reference
}