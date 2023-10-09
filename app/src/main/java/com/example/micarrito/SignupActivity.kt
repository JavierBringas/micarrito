package com.example.micarrito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.micarrito.databinding.ActivitySignupBinding
import com.example.micarrito.utils.functions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * SignupActivity allows users to sign up for the application by providing their email and password.
 * After successful registration, users are redirected to the 'HomeActivity'.
 */
class SignupActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    /**
     * Sets click listeners.
     */
    private fun setListeners() {
        binding.submitButton.setOnClickListener {
            attemptSignup()
        }

        binding.loginTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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
            functions.message(baseContext, "Email and password cannot be empty.")
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
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    initializeShoppingList()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    functions.message(baseContext, task.exception?.message)
                }
            }
    }

    /**
     * Initializes the shopping list for the current user in Firestore.
     *
     * Creates a new shopping list with a default name and adds a default product to it.
     *
     * This function is called upon successful user registration.
     */
    private fun initializeShoppingList() {
        val user = auth.currentUser

        if (user != null) {
            val shoppingListsReference =
                db.collection("users")
                    .document(user.uid)
                    .collection("shoppingLists")

            shoppingListsReference.add(hashMapOf("name" to "Shopping list"))
                .addOnSuccessListener { document ->
                    shoppingListsReference
                        .document(document.id)
                        .collection("products")
                        .add(
                            hashMapOf(
                                "name" to "Product"
                            )
                        )
                }
        }
    }
}