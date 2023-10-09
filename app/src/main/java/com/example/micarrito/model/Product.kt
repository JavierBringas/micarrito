package com.example.micarrito.model

/**
 * Represents a product with a name.
 *
 * @property document ID of the document where product is stored.
 * @property name The name of the product.
 */
data class Product(val document: String, val name: String) {
    constructor() : this("", "")

}
