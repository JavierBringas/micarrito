package com.example.micarrito.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.micarrito.R
import com.example.micarrito.model.ShoppingList

class ShoppingListViewHolder(view: View) : ViewHolder(view) {

    val shoppingListName = view.findViewById<TextView>(R.id.nameTextView)

    fun render(shoppingListModel: ShoppingList) {
        shoppingListName.text = shoppingListModel.name
    }
}
