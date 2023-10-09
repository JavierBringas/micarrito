package com.example.micarrito.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.micarrito.databinding.ProductItemBinding
import com.example.micarrito.model.Product

/**
 * ViewHolder class for displaying a single product item in a RecyclerView.
 *
 * @param view The view that represents a product item.
 */
class ProductViewHolder(view: View) : ViewHolder(view) {

    val binding = ProductItemBinding.bind(view)

    /**
     * Renders the data of a product item by setting the name of the shopping list.
     *
     * @param shoppingListModel The [ShoppingList] model containing the data to be displayed.
     */
    fun render(productModel: Product, onClickListener: (Product) -> Unit) {
        binding.nameTextView.text = productModel.name
        binding.deleteButton.setOnClickListener {
            onClickListener(productModel)
        }
    }
}
