package com.example.micarrito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.micarrito.databinding.ActivityMainBinding

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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    /**
     * Sets click listeners.
     */
    private fun setListeners() {
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

}