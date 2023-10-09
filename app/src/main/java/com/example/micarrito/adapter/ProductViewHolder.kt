package com.example.micarrito.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.micarrito.R
import com.example.micarrito.model.Product

/**
 * ViewHolder class for displaying a single product item in a RecyclerView.
 *
 * @param view The view that represents a product item.
 */
class ProductViewHolder(view: View) : ViewHolder(view) {

    /**
     * Reference to the TextView for displaying the product name.
     */
    val productName = view.findViewById<TextView>(R.id.nameTextView)

    /**
     * Renders the data of a product item by setting the name of the shopping list.
     *
     * @param shoppingListModel The [ShoppingList] model containing the data to be displayed.
     */
    fun render(productModel: Product) {
        productName.text = productModel.name
    }
}
