package com.example.micarrito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.micarrito.databinding.ActivityMainBinding

/**
 * Runs the application.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}