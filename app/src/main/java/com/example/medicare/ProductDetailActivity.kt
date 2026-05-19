package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    private var quantity = 1
    private var price = 12.0
    private var productName = ""
    private var imageRes = R.drawable.vitamin_c

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        // Get data from intent
        productName = intent.getStringExtra("PRODUCT_NAME") ?: "Vitamin C 500mg"
        price = intent.getDoubleExtra("PRODUCT_PRICE", 12.0)
        imageRes = intent.getIntExtra("PRODUCT_IMAGE", R.drawable.vitamin_c)

        // Set UI
        findViewById<TextView>(R.id.txtProductTitle).text = productName
        findViewById<TextView>(R.id.txtProductName).text = productName
        findViewById<TextView>(R.id.txtPrice).text = "$ $price"
        findViewById<ImageView>(R.id.imgProduct).setImageResource(imageRes)

        val txtQuantity = findViewById<TextView>(R.id.txtQuantity)
        val txtPrice = findViewById<TextView>(R.id.txtPrice)

        // Plus button
        findViewById<Button>(R.id.btnPlus).setOnClickListener {
            quantity++
            txtQuantity.text = quantity.toString()
            txtPrice.text = "$ ${String.format("%.2f", price * quantity)}"
        }

        // Minus button
        findViewById<Button>(R.id.btnMinus).setOnClickListener {
            if (quantity > 1) {
                quantity--
                txtQuantity.text = quantity.toString()
                txtPrice.text = "$ ${String.format("%.2f", price * quantity)}"
            }
        }

        // Add to Cart
        findViewById<Button>(R.id.btnAddToCart).setOnClickListener {
            CartManager.addItem(CartItem(productName, price, imageRes, quantity))
            Toast.makeText(this, "$productName added to cart!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, CartActivity::class.java))
        }

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}