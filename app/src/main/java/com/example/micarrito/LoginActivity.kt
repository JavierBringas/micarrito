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

/**
 * LoginActivity allows users to log in to the application by providing their email and password.
 * After successful login, users are redirected to the 'HomeActivity'.
 */
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

    /**
     * Sets click listeners.
     */
    private fun setListeners() {
        submitButton.setOnClickListener {
            attemptLogin()
        }

        signupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Attempts to log in the user using the provided email and password.
     *
     * If either the email or password is empty, it displays an error message.
     *
     * If both email and password are provided, it calls the 'login' function
     * to perform the login attempt.
     */
    private fun attemptLogin() {
        val email = editTextTextEmailAddress.text.toString()
        val password = editTextTextPassword.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(
                baseContext,
                "Email and password cannot be empty.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            login(email, password)
        }
    }


    /**
     * Attempts to log in the user with the provided email and password using Firebase
     * Authentication.
     *
     * If the login is successful, it navigates to the 'HomeActivity'.
     *
     * If the login fails due to invalid credentials, it displays an error message.
     *
     * @param email email of the user.
     * @param password password of the user.
     */
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