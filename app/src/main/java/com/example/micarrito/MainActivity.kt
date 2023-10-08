package com.example.micarrito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/**
 * MainActivity represents the main entry point of the application.
 *
 * It displays options for users to either log in or sign up.
 *
 * Clicking on the "Log In" button navigates the user to the LoginActivity.
 *
 * Clicking on the "Sign Up" button navigates the user to the SignupActivity.
 */
class MainActivity : AppCompatActivity() {

    private val loginButton by lazy { findViewById<Button>(R.id.loginButton) }
    private val signupButton by lazy { findViewById<Button>(R.id.signupButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    /**
     * Sets click listeners.
     */
    private fun setListeners() {
        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

}