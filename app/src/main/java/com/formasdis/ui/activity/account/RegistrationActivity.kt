package com.formasdis.ui.activity.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.formasdis.R
import com.formasdis.network.DataUser
import com.formasdis.network.User
import com.formasdis.ui.activity.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()

        val toolBarTitle = findViewById<TextView>(R.id.titleToolBar)
        val textInputLogin = findViewById<TextInputEditText>(R.id.textInputEmailRegistration)
        val textInputPassword = findViewById<TextInputEditText>(R.id.textInputPasswordRegistration)
        val textInputPasswordConfirmation =
            findViewById<TextInputEditText>(R.id.textInputPasswordConfirmationRegistration)
        val buttonRegistration = findViewById<Button>(R.id.buttonRegistration)
        val buttonLogin = findViewById<Button>(R.id.buttonLoginRegistration)

        toolBarTitle.visibility = View.VISIBLE
        toolBarTitle.text = "Inscription"

        buttonRegistration.setOnClickListener {
            if (textInputPassword.text.toString() == textInputPasswordConfirmation.text.toString()) {
                auth.createUserWithEmailAndPassword(
                    textInputLogin.text.toString(),
                    textInputPassword.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Dim", "createUserWithEmail:success")

                            User.uID = auth.currentUser?.uid.toString()
                            DataUser.writeNewUser()

                            auth.signInWithEmailAndPassword(
                                textInputLogin.text.toString(),
                                textInputPassword.text.toString()
                            )
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("Dim", "signInWithEmail:success")

                                        User.uID = auth.currentUser?.uid ?: ""

                                        DataUser.loadUser()

                                        val intent = Intent(this, MainActivity::class.java)
                                        this.startActivity(intent)
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.d("Dim", "signInWithEmail:failure", task.exception)
                                        Toast.makeText(
                                            baseContext, "Connexion échoué.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val intent = Intent(this, LoginActivity::class.java)
                                        this.startActivity(intent)
                                    }
                                }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("Dim", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Inscripiton échoué.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    baseContext, "Les deux mots de passes ne sont pas identiques.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
        }
    }
}