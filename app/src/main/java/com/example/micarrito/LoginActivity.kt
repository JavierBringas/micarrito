package com.example.micarrito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextTextEmailAddress: EditText =
            findViewById<EditText>(R.id.editTextTextEmailAddress)
        val editTextTextPassword: EditText =
            findViewById<EditText>(R.id.editTextTextPassword)
        val submitButton: Button = findViewById<Button>(R.id.submitButton)
        firebaseAuth = Firebase.auth

        submitButton.setOnClickListener {
            login(editTextTextEmailAddress.text.toString(), editTextTextPassword.text.toString())
        }

    }

    private fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
    }
}