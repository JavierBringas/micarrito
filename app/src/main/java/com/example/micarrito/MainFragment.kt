package com.example.micarrito

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.micarrito.databinding.FragmentMainBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * Represents the main entry point of the application.
 *
 * It displays options for users to either log in or sign up.
 *
 * Clicking on the "Log In" button navigates the user to the LoginActivity.
 *
 * Clicking on the "Sign Up" button navigates the user to the SignupActivity.
 */
class MainFragment : Fragment() {

    private val auth = FirebaseAuth.getInstance()

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        start()
        return binding.root
    }

    /**
     * Starts the fragment by checking if the user is already authenticated.
     *
     * If the user is authenticated, it navigates them to the home screen (homeFragment). If not, it
     * sets up click listeners for the "Log In" and "Sign Up" buttons, allowing navigation to the
     * respective screens.
     */
    private fun start() {
        if (auth.currentUser != null) {
            findNavController().navigate(R.id.action_mainFragment_to_homeFragment)
        } else {
            setListeners()
        }
    }

    /**
     * Sets click listeners.
     */
    private fun setListeners() {
        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }

        binding.signupButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_signupFragment)
        }
    }

}