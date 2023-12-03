package com.example.micarrito.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.micarrito.R
import com.example.micarrito.model.Product

/**
 * Adapter class for displaying a list of products in a RecyclerView.
 *
 * @property products The list of products to be displayed.
 */
class ProductsAdapter(
    private val products: List<Product>,
    private val onDeleteClickListener: (Product) -> Unit,
    private val onCheckClickListener: (Product) -> Unit
) :
    RecyclerView.Adapter<ProductViewHolder>() {

    /**
     * Called when RecyclerView needs a new [ProductViewHolder] of the given type to represent
     * an item.
     *
     * @param parent The parent view group that the View will eventually be attached to.
     * @param viewType The view type of the new View.
     * @return A new [ProductViewHolder] that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(
            layoutInflater.inflate(
                R.layout.product_item, parent, false
            )
        )
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in the data set.
     */
    override fun getItemCount(): Int = products.size;

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The [ProductViewHolder] which should be updated to represent
     * the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = products[position]
        holder.render(item, onDeleteClickListener, onCheckClickListener)
    }
}