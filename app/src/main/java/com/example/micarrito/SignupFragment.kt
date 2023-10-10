package com.example.micarrito

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.micarrito.databinding.FragmentSignupBinding
import com.example.micarrito.utils.functions
import com.google.firebase.auth.FirebaseAuth

/**
 * Allows users to sign up for the application by providing their email and password.
 * After successful registration, users are redirected to the 'HomeActivity'.
 */
class SignupFragment : Fragment() {

    private val auth = FirebaseAuth.getInstance()

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        setListeners()
        return binding.root
    }

    /**
     * Sets click listeners.
     */
    private fun setListeners() {
        binding.submitButton.setOnClickListener {
            attemptSignup()
        }

        binding.loginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }

    /**
     * Attempts to sign up a user with the provided email and password.
     *
     * If either the email or password is empty, it displays an error message.
     *
     * If both email and password are provided, it calls the 'signup' function
     * to perform the registration attempt.
     */
    private fun attemptSignup() {
        val email = binding.editTextTextEmailAddress.text.toString()
        val password = binding.editTextTextPassword.text.toString()

        if (email.isBlank() || password.isBlank()) {
            functions.message(requireContext(), "Email and password cannot be empty.")
        } else {
            signup(email, password)
        }
    }


    /**
     * Attempts to sign up a user with the provided email and password using Firebase
     * Authentication.
     *
     * If the registration is successful, it initializes the user's shopping list and navigates to
     * the 'HomeActivity'.
     *
     * If the registration fails, it displays an error message with the reason for failure.
     *
     * @param email email of the user.
     * @param password password of the user.
     */
    private fun signup(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
                } else {
                    functions.message(requireContext(), task.exception?.message)
                }
            }
    }

}