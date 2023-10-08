package com.example.micarrito.utils

import android.content.Context
import android.widget.Toast

object functions {
    /**
     * Displays an error message as a Toast with the specified message.
     *
     * @param context The [Context] in which the Toast should be displayed.
     * @param message The error message to be displayed.
     */
    fun showErrorMessage(context: Context, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}