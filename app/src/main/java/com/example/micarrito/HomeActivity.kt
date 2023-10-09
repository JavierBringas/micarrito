package com.example.micarrito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.micarrito.adapter.ProductsAdapter
import com.example.micarrito.databinding.ActivityHomeBinding
import com.example.micarrito.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * HomeActivity displays the products associated with the logged-in user.
 * It retrieves products from Firestore and displays them using a RecyclerView.
 */
class HomeActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        start()
    }

    /**
     * Runs the activity.
     */
    private fun start() {
        val user = auth.currentUser
        if (user != null) {
            load(user.uid)
        }
    }

    /**
     * Loads the products associated with the user from Firestore based on the provided user
     * ID.
     *
     * Retrieves products from the 'products' collection and initializes the
     * RecyclerView to display them.
     *
     * @param id ID of the user used to identify their products in Firestore.
     */
    private fun load(id: String) {
        db.collection("users")
            .document(id)
            .collection("products")
            .get()
            .addOnSuccessListener { documents ->
                val products = ArrayList<Product>()
                for (document in documents) {
                    val product = document.toObject(Product::class.java)
                    products.add(product)
                }
                initializeRecyclerView(products)
            }
    }

    /**
     * Initializes the RecyclerView to display products.
     * @param products The list of products to display.
     */
    private fun initializeRecyclerView(products: ArrayList<Product>) {
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.productsRecyclerView.adapter = ProductsAdapter(products)
    }

}