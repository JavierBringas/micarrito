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

class SignupActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth = Firebase.auth

        val editTextTextEmailAddress = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val editTextTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val loginButton = findViewById<TextView>(R.id.loginTextView)

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

            signup(email, password)
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signup(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
//                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext,
                        task.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}