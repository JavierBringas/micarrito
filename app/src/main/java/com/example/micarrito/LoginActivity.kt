package com.example.micarrito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.micarrito.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.example.micarrito.utils.functions

/**
 * LoginActivity allows users to log in to the application by providing their email and password.
 * After successful login, users are redirected to the 'HomeActivity'.
 */
class LoginActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    /**
     * Sets click listeners.
     */
    private fun setListeners() {
        binding.submitButton.setOnClickListener {
            attemptLogin()
        }

        binding.signupTextView.setOnClickListener {
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
        val email = binding.editTextTextEmailAddress.text.toString()
        val password = binding.editTextTextPassword.text.toString()

        if (email.isBlank() || password.isBlank()) {
            functions.message(baseContext, "Email and password cannot be empty.")
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
                    functions.message(baseContext, "Invalid credentials.")
                }
            }
    }
}