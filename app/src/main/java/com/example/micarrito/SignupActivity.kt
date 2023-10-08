package com.example.micarrito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val editTextTextEmailAddress by lazy { findViewById<EditText>(R.id.editTextTextEmailAddress) }
    private val editTextTextPassword by lazy { findViewById<EditText>(R.id.editTextTextPassword) }
    private val submitButton by lazy { findViewById<Button>(R.id.submitButton) }
    private val loginButton by lazy { findViewById<TextView>(R.id.loginTextView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
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

            signup(email, password)
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signup(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    initializeShoppingList()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext,
                        task.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

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