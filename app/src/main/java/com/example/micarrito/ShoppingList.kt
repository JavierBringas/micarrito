package com.example.micarrito

data class ShoppingList(
    val name: String,
    val products: List<Product>
) {
    constructor() : this("", ArrayList())
}
