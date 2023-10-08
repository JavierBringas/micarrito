package com.example.micarrito.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.micarrito.R
import com.example.micarrito.model.ShoppingList

class ShoppingListAdapter(private val shoppingListList: List<ShoppingList>) :
    RecyclerView.Adapter<ShoppingListViewHolder>() {

    constructor() : this(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ShoppingListViewHolder(
            layoutInflater.inflate(
                R.layout.item_shopping_list, parent, false
            )
        )
    }

    override fun getItemCount(): Int = shoppingListList.size;

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val item = shoppingListList[position]
        holder.render(item)
    }
}