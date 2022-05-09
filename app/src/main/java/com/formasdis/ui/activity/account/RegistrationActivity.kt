package com.formasdis.ui.activity.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.formasdis.R
import com.formasdis.ui.activity.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = Firebase.auth

        val toolBarTitle = findViewById<TextView>(R.id.titleToolBar)
        val textInputLogin = findViewById<TextInputEditText>(R.id.textInputEmailRegistration)
        val textInputPassword = findViewById<TextInputEditText>(R.id.textInputPasswordRegistration)
        val buttonRegistration = findViewById<Button>(R.id.buttonRegistration)
        val buttonLogin = findViewById<Button>(R.id.buttonLoginRegistration)

        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Inscription"

        buttonRegistration.setOnClickListener {
            auth.createUserWithEmailAndPassword(textInputLogin.text.toString(), textInputPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Dim", "createUserWithEmail:success")
                        val user = auth.currentUser
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("Dim", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }
                }
        }

        buttonLogin.setOnClickListener {
            val intent = Intent (this, LoginActivity::class.java)
            this.startActivity(intent)
        }
    }
}