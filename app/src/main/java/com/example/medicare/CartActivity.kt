package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var txtSubtotal: TextView
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        txtSubtotal = findViewById(R.id.txtSubtotal)
        val recycler = findViewById<RecyclerView>(R.id.recyclerCart)

        recycler.layoutManager = LinearLayoutManager(this)

        adapter = CartAdapter(CartManager.cartItems) {
            updateSubtotal()
        }
        recycler.adapter = adapter

        updateSubtotal()

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnAddMoreItems).setOnClickListener {
            startActivity(Intent(this, VitaminsActivity::class.java))
        }

        findViewById<Button>(R.id.btnCheckout).setOnClickListener {
            if (CartManager.cartItems.isEmpty()) {
                Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, CheckoutActivity::class.java))
                finish()
            }
        }

        // Bottom Navigation
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        findViewById<LinearLayout>(R.id.navNotification).setOnClickListener {
            // add NotificationActivity when ready
        }
        findViewById<LinearLayout>(R.id.navProfile).setOnClickListener {
            // add ProfileActivity when ready
        }
        findViewById<LinearLayout>(R.id.navReport).setOnClickListener {
            // add ReportActivity when ready
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        updateSubtotal()
    }

    private fun updateSubtotal() {
        txtSubtotal.text = "$ ${String.format("%.2f", CartManager.getTotal())}"
    }
}