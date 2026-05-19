package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Load name from SharedPreferences
        val prefs    = getSharedPreferences("MedicareProfile", MODE_PRIVATE)
        val userName = prefs.getString("name", "User")
        findViewById<TextView>(R.id.tvWelcome).text = "Welcome! $userName"

        // Quick action buttons
        findViewById<LinearLayout>(R.id.btnTopDoctors).setOnClickListener {
            startActivity(Intent(this, TopDoctorsActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btnAmbulance).setOnClickListener {
            startActivity(Intent(this, AmbulanceActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btnPharmacy).setOnClickListener {
            startActivity(Intent(this, PharmacyActivity::class.java))
        }

        // Bottom navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_notification -> {
                    val intent = Intent(this, HelpActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    startActivity(intent)
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    startActivity(intent)
                    true
                }
                R.id.nav_report -> {
                    val intent = Intent(this, OrderTrackingActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        // Back press
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                startActivity(Intent(this@HomeActivity, WelcomeActivity::class.java))
                finish()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        // Reset bottom nav to Home tab when returning
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .selectedItemId = R.id.nav_home
    }
}