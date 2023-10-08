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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    private val editTextTextEmailAddress by lazy { findViewById<EditText>(R.id.editTextTextEmailAddress) }
    private val editTextTextPassword by lazy { findViewById<EditText>(R.id.editTextTextPassword) }
    private val submitButton by lazy { findViewById<Button>(R.id.submitButton) }
    private val signupButton by lazy { findViewById<TextView>(R.id.signupTextView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListeners()
    }

    private fun setListeners() {
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
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
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