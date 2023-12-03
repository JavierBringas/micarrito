package com.example.micarrito.adapter

import android.graphics.Paint
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
     * @param productModel The [Product] model containing the data to be displayed.
     * @param onDeleteClickListener The click listener for the delete button.
     * @param onCheckClickListener The click listener for the CheckBox.
     */
    fun render(
        productModel: Product,
        onDeleteClickListener: (Product) -> Unit,
        onCheckClickListener: (Product) -> Unit
    ) {
        binding.nameCheckBox.text = productModel.name
        binding.nameCheckBox.isChecked = productModel.checked

        updateTextStrikeThrough(productModel.checked)

        binding.deleteButton.setOnClickListener {
            onDeleteClickListener(productModel)
        }
        binding.nameCheckBox.setOnClickListener {
            onCheckClickListener(productModel)
        }
    }

    /**
     * Updates the strikethrough style of the CheckBox text based on the provided state.
     * @param isChecked Indicates whether the [Product] is checked or not.
     */
    private fun updateTextStrikeThrough(isChecked: Boolean) {
        binding.nameCheckBox.paintFlags =
            if (isChecked) {
                binding.nameCheckBox.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                binding.nameCheckBox.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
    }
}


