package com.example.micarrito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = Firebase.auth

        val editTextTextEmailAddress = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val editTextTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val signupButton = findViewById<TextView>(R.id.signupTextView)

        submitButton.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString()
            val password = editTextTextPassword.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(
                    baseContext,
                    "Email and password cannot be empty.",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            login(email, password)
        }

        signupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    private fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
//                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext,
                        "Invalid credentials.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}