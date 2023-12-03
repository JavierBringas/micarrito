package com.example.micarrito.model

/**
 * Represents a product with a name.
 *
 * @property document ID of the document where product is stored.
 * @property name The name of the product.
 * @property checked Indicates whether the product has been checked or not.
 */
data class Product(val document: String, val name: String, val checked: Boolean) {
    constructor() : this("", "", false)

}
