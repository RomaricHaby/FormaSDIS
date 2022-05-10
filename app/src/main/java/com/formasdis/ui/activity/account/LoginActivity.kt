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
import com.formasdis.network.DataUser
import com.formasdis.network.User
import com.formasdis.ui.activity.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth

        val toolBarTitle = findViewById<TextView>(R.id.titleToolBar)
        val textInputLogin = findViewById<TextInputEditText>(R.id.textInputEmailLogin)
        val textInputPassword = findViewById<TextInputEditText>(R.id.textInputPasswordLogin)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonRegistration = findViewById<Button>(R.id.buttonRegistrationLogin)

        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Connexion"

        buttonLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(textInputLogin.text.toString(), textInputPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Dim", "signInWithEmail:success")

                        User.uID = auth.currentUser?.uid ?: ""

                        DataUser.loadUser()

                        val intent = Intent (this, MainActivity::class.java)
                        this.startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("Dim", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }
                }
        }

        buttonRegistration.setOnClickListener {
            val intent = Intent (this, RegistrationActivity::class.java)
            this.startActivity(intent)
        }
    }
}