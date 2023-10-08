package com.example.micarrito.model

data class ShoppingList(
    val name: String,
    val products: List<Product>
) {
    constructor() : this("", ArrayList())
}
