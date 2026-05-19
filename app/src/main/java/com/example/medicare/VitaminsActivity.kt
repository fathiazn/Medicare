package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VitaminsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitamins)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        setupButton(R.id.btnAddVitaminC, "Vitamin C 500 mg", 12.00, R.drawable.vitamin_c)
        setupButton(R.id.btnAddOmega3, "Omega 3 Fish Oil", 18.50, R.drawable.omega3)
        setupButton(R.id.btnAddCalcium, "Calcium + D3 Tablets", 9.99, R.drawable.calcium)
        setupButton(R.id.btnAddMultivitamin, "Multivitamin Daily", 15.99, R.drawable.multivitamin)
        setupButton(R.id.btnAddZinc, "Zinc 50mg Tablets", 7.49, R.drawable.zinc)

        // Bottom Navigation
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        findViewById<LinearLayout>(R.id.navNotification).setOnClickListener { }
        findViewById<LinearLayout>(R.id.navProfile).setOnClickListener { }
        findViewById<LinearLayout>(R.id.navReport).setOnClickListener { }
    }

    private fun setupButton(buttonId: Int, name: String, price: Double, image: Int) {
        findViewById<Button>(buttonId).setOnClickListener {
            CartManager.addItem(CartItem(name, price, 1, image))
            Toast.makeText(this, "$name added!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, CartActivity::class.java))
            finish()
        }
    }
}