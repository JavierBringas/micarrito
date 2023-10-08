package com.example.micarrito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (auth.currentUser != null) {
            val id = auth.currentUser?.uid
            load(id!!)
        }
    }

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

    private fun initializeRecyclerView(shoppingListList: ArrayList<ShoppingList>) {
        val recyclerView = findViewById<RecyclerView>(R.id.shoppingListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ShoppingListAdapter(shoppingListList)
    }

}