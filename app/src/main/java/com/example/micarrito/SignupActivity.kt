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

val EMAIL_REGULAR_EXPRESSION = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

class SignupActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

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

            if (!isValidEmail(email)) {
                Toast.makeText(baseContext, "Invalid email format", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            signup(email, editTextTextPassword.text.toString())
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.matches(EMAIL_REGULAR_EXPRESSION.toRegex())
    }

    private fun signup(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext,
                        "Something went wrong... Try again later",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}