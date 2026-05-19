package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PharmacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharmacy)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<ImageButton>(R.id.btnCart).setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
            finish()
        }

        // Navigate to Vitamins category
        findViewById<LinearLayout>(R.id.catVitamins).setOnClickListener {
            startActivity(Intent(this, VitaminsActivity::class.java))
            finish()
        }

        // Add Panadol
        findViewById<Button>(R.id.btnBuyPanadol).setOnClickListener {
            CartManager.addItem(CartItem("Panadol Extra", 5.99, 1, R.drawable.panadol))
            Toast.makeText(this, "Panadol added to cart!", Toast.LENGTH_SHORT).show()
        }

        // Add Zyrtec
        findViewById<Button>(R.id.btnBuyZyrtec).setOnClickListener {
            CartManager.addItem(CartItem("Zyrtec Tablets", 8.99, 1, R.drawable.zyrtec))
            Toast.makeText(this, "Zyrtec added to cart!", Toast.LENGTH_SHORT).show()
        }

        // Bottom Navigation
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        findViewById<LinearLayout>(R.id.navNotification).setOnClickListener { }
        findViewById<LinearLayout>(R.id.navProfile).setOnClickListener { }
        findViewById<LinearLayout>(R.id.navReport).setOnClickListener { }
    }
}