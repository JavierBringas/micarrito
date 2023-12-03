package com.example.micarrito

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.micarrito.databinding.FragmentLoginBinding
import com.example.micarrito.utils.functions
import com.google.firebase.auth.FirebaseAuth

/**
 * Allows users to log in to the application by providing their email and password.
 * After successful login, users are redirected to the 'HomeActivity'.
 */
class LoginFragment : Fragment() {

    private val auth = FirebaseAuth.getInstance()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        start()
        return binding.root
    }

    /**
     * Starts the fragment by checking if the user is already authenticated.
     *
     * If the user is authenticated, it navigates them to the home screen (homeFragment). If not, it
     * sets up click listeners for login action or navigation to the signup screen.
     */
    private fun start() {
        if (auth.currentUser != null) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        } else {
            setListeners()
        }
    }

    /**
     * Sets click listeners.
     */
    private fun setListeners() {
        binding.submitButton.setOnClickListener {
            attemptLogin()
        }

        binding.signupTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
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
            functions.message(requireContext(), "Email and password cannot be empty.")
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
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    functions.message(requireContext(), "Invalid credentials.")
                }
            }
    }

}