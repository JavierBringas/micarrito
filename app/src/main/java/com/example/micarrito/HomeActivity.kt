package com.example.micarrito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.micarrito.adapter.ProductsAdapter
import com.example.micarrito.databinding.ActivityHomeBinding
import com.example.micarrito.model.Product
import com.example.micarrito.utils.functions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * HomeActivity displays the products associated with the logged-in user.
 * It retrieves products from Firestore and displays them using a RecyclerView.
 */
class HomeActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    /**
     * The unique identifier (ID) of the currently authenticated user.
     * This variable is used to store the user's ID once they are authenticated.
     * It is utilized to access user-specific data in Firestore and perform operations
     * related to the authenticated user.
     */
    private lateinit var userId: String

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        start()
    }

    /**
     * Sets click listeners except those related to each product (e.g.: for deleting a product).
     */
    private fun setListeners() {
        binding.addButton.setOnClickListener {
            add()
        }
    }

    /**
     * Starts the activity checking if the user is authenticated initializating listeners and
     * loading products associated with the current user from Firestore.
     */
    private fun start() {
        val user = auth.currentUser
        if (user != null) {
            userId = user.uid
            setListeners()
            load()
        }
    }

    /**
     * Loads the products associated with the user from Firestore based on [userId].
     *
     * Retrieves products from the 'products' collection and initializes the RecyclerView to display
     * them.
     */
    private fun load() {
        db.collection("users")
            .document(userId)
            .collection("products")
            .get()
            .addOnSuccessListener { documents ->
                val products = ArrayList<Product>()
                for (document in documents) {
                    val product = document.toObject(Product::class.java)
                    products.add(product)
                }
                setRecyclerView(products)
            }
    }

    /**
     * Updates the RecyclerView to display products.
     * @param products The list of products to display.
     */
    private fun setRecyclerView(products: ArrayList<Product>) {
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.productsRecyclerView.adapter =
            ProductsAdapter(products, { delete(it.document) })
    }

    /**
     * Deletes a product document from Firestore based on [userId] and its document.
     * @param document Product document ID to be deleted.
     */
    private fun delete(document: String) {
        db.collection("users")
            .document(userId)
            .collection("products")
            .document(document)
            .delete()
            .addOnSuccessListener {
                load()
            }
    }

    private fun add() {
        val productName = binding.editTextTextProductName.text.toString()
        if (productName.isBlank()) {
            functions.message(baseContext, "Product names must not be blank.")
        } else {
            db.collection("users")
                .document(userId)
                .collection("products")
                .add(hashMapOf("name" to productName))
                .addOnSuccessListener { document ->
                    document.update("document", document.id)
                    load()
                }
        }
    }

}