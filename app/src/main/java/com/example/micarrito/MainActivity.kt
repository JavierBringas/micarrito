package com.example.micarrito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.micarrito.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES


/**
 * Runs the application.
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
        binding.switchMode.setOnCheckedChangeListener { _, isSelected ->
            if (isSelected) {
                enableDarkMode()
            } else {
                disableDarkMode()
            }
        }
    }

    /**
     * Enables dark mode in the entire application.
     *
     * Changes the default theme of the application to dark mode and applies the changes
     * immediately.
     */
    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    /**
     * Disables dark mode in the entire application.
     *
     * Changes the default theme of the application to light mode and applies the changes
     * immediately.
     */
    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

}