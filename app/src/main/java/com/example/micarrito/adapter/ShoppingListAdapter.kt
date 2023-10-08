package com.example.micarrito.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.micarrito.R
import com.example.micarrito.model.ShoppingList

/**
 * Adapter class for displaying a list of shopping lists in a RecyclerView.
 *
 * @property shoppingListList The list of shopping lists to be displayed.
 */
class ShoppingListAdapter(private val shoppingListList: List<ShoppingList>) :
    RecyclerView.Adapter<ShoppingListViewHolder>() {

    constructor() : this(emptyList())

    /**
     * Called when RecyclerView needs a new [ShoppingListViewHolder] of the given type to represent
     * an item.
     *
     * @param parent The parent view group that the View will eventually be attached to.
     * @param viewType The view type of the new View.
     * @return A new [ShoppingListViewHolder] that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ShoppingListViewHolder(
            layoutInflater.inflate(
                R.layout.item_shopping_list, parent, false
            )
        )
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in the data set.
     */
    override fun getItemCount(): Int = shoppingListList.size;

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The [ShoppingListViewHolder] which should be updated to represent
     * the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val item = shoppingListList[position]
        holder.render(item)
    }
}