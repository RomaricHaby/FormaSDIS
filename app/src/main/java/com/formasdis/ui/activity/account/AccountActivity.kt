package com.formasdis.ui.activity.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.formasdis.R
import com.formasdis.network.DataUser
import com.formasdis.ui.activity.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        auth = Firebase.auth

        val toolBarTitle = findViewById<TextView>(R.id.titleToolBar)
        val toolBarBack = findViewById<ImageButton>(R.id.imageButtonBack)

        val textEmail = findViewById<TextView>(R.id.textViewEmail)
        val buttonDisconnection = findViewById<Button>(R.id.buttonDisconnection)
        val buttonDeleteAccount = findViewById<Button>(R.id.buttonDeleteAccount)

        toolBarTitle.visibility = View.VISIBLE
        toolBarBack.visibility = View.VISIBLE
        toolBarTitle.text = "Votre compte"

        textEmail.text = textEmail.text.toString() + " " + auth.currentUser?.email

        buttonDeleteAccount.setOnClickListener {
            DataUser.clearData()
        }

        buttonDisconnection.setOnClickListener {
            Firebase.auth.signOut()

            val intent = Intent (this, MainActivity::class.java)
            this.startActivity(intent)
        }

        toolBarBack.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }
}