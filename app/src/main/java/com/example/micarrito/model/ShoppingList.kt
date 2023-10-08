package com.example.micarrito.model

/**
 * Represents a shopping list with a name and a list of products.
 *
 * @property name The name of the shopping list.
 * @property products A list of products within the shopping list.
 */
data class ShoppingList(
    val name: String,
    val products: List<Product>
) {
    constructor() : this("", ArrayList())
}
