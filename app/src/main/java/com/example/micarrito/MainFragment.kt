package com.example.micarrito

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.micarrito.databinding.FragmentMainBinding

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

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setListeners()
        return binding.root
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