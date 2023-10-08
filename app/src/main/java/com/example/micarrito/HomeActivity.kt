package com.example.micarrito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.micarrito.adapter.ShoppingListAdapter
import com.example.micarrito.model.ShoppingList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * HomeActivity displays the shopping lists associated with the logged-in user.
 * It retrieves shopping lists from Firestore and displays them using a RecyclerView.
 */
class HomeActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
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
     * Loads the shopping lists associated with the user from Firestore based on the provided user
     * ID.
     *
     * Retrieves shopping lists from the 'shoppingLists' collection and initializes the
     * RecyclerView to display them.
     *
     * @param id ID of the user used to identify their shopping lists in Firestore.
     */
    private fun load(id: String) {
        db.collection("users")
            .document(id)
            .collection("shoppingLists")
            .get()
            .addOnSuccessListener { documents ->
                val shoppingListList = ArrayList<ShoppingList>()
                for (document in documents) {
                    val shoppingList = document.toObject(ShoppingList::class.java)
                    shoppingListList.add(shoppingList)
                }
                initializeRecyclerView(shoppingListList)
            }
    }

    /**
     * Initializes the RecyclerView to display shopping lists.
     * @param shoppingListList The list of shopping lists to display.
     */
    private fun initializeRecyclerView(shoppingListList: ArrayList<ShoppingList>) {
        val recyclerView = findViewById<RecyclerView>(R.id.shoppingListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ShoppingListAdapter(shoppingListList)
    }

}