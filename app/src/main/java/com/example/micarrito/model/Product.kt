package com.example.micarrito.model

/**
 * Represents a product with a name.
 *
 * @property name The name of the product.
 */
data class Product(val name: String) {
    constructor() : this("")
}
