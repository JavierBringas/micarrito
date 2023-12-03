package com.example.micarrito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.micarrito.adapter.ProductsAdapter
import com.example.micarrito.model.Product
import com.example.micarrito.utils.functions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import com.example.micarrito.databinding.FragmentHomeBinding

/**
 * Displays the products associated with the logged-in user.
 * It retrieves products from Firestore and displays them using a RecyclerView.
 */
class HomeFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    /**
     * The unique identifier (ID) of the currently authenticated user.
     * This variable is used to store the user's ID once they are authenticated.
     * It is utilized to access user-specific data in Firestore and perform operations
     * related to the authenticated user.
     */
    private lateinit var userId: String

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        start()
        return binding.root
    }

    /**
     * Sets click listeners except those related to each product (e.g.: for deleting all products).
     */
    private fun setListeners() {
        binding.addButton.setOnClickListener {
            add()
        }

        binding.logoutButton.setOnClickListener {
            logout()
        }

        binding.deleteAllButton.setOnClickListener {
            deleteAll()
        }
    }

    /**
     * Starts the fragment checking if the user is authenticated initializating listeners and
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
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.productsRecyclerView.adapter = ProductsAdapter(
            products,
            onDeleteClickListener = { delete(it.document) },
            onCheckClickListener = { check(it.document) }
        )
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

    /**
     * Adds a product to the Firestore collection of the user, validating the product name.
     */
    private fun add() {
        val productName = binding.editTextTextProductName.text.toString()
        if (productName.isBlank()) {
            functions.message(requireContext(), "Product names must not be blank.")
        } else {
            db.collection("users")
                .document(userId)
                .collection("products")
                .add(hashMapOf("name" to productName, "checked" to false))
                .addOnSuccessListener { document ->
                    document.update("document", document.id)
                    load()
                    binding.editTextTextProductName.setText("");
                }
        }
    }

    /**
     * Logs the user out by signing them out of their current session and navigates to the main
     * screen.
     */
    private fun logout() {
        auth.signOut()
        findNavController().navigate(R.id.action_homeFragment_to_mainFragment)
    }

    /**
     * Deletes all products associated with the user in Firestore.
     */
    private fun deleteAll() {
        db.collection("users")
            .document(userId)
            .collection("products")
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    delete(document.id)
                }
            }
    }

    /**
     * Marks a product as checked or unchecked in Firestore based on [userId] and its document.
     * @param document Product document ID to be updated.
     */
    private fun check(document: String) {
        db.collection("users")
            .document(userId)
            .collection("products")
            .document(document)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val product = documentSnapshot.toObject(Product::class.java)
                if (product != null) {
                    documentSnapshot.reference
                        .update("checked", !product.checked)
                        .addOnSuccessListener {
                            load()
                        }
                }
            }
    }

}