package com.example.micarrito

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ShoppingListViewHolder(view: View) : ViewHolder(view) {

    val shoppingListName = view.findViewById<TextView>(R.id.nameTextView)

    fun render(shoppingListModel: ShoppingList) {
        shoppingListName.text = shoppingListModel.name
    }
}
