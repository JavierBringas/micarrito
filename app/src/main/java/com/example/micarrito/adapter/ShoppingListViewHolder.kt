package com.example.micarrito.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.micarrito.R
import com.example.micarrito.model.ShoppingList

/**
 * ViewHolder class for displaying a single shopping list item in a RecyclerView.
 *
 * @param view The view that represents a shopping list item.
 */
class ShoppingListViewHolder(view: View) : ViewHolder(view) {

    /**
     * Reference to the TextView for displaying the shopping list name.
     */
    val shoppingListName = view.findViewById<TextView>(R.id.nameTextView)

    /**
     * Renders the data of a shopping list item by setting the name of the shopping list.
     *
     * @param shoppingListModel The [ShoppingList] model containing the data to be displayed.
     */
    fun render(shoppingListModel: ShoppingList) {
        shoppingListName.text = shoppingListModel.name
    }
}
