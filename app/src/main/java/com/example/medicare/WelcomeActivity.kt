package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        // ✅ Hamburger menu open
        findViewById<TextView>(R.id.btnMenu).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        // Login button
        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Signup button
        findViewById<Button>(R.id.btnSignup).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Drawer menu items
        findViewById<LinearLayout>(R.id.menuHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            drawerLayout.closeDrawers()
        }
        findViewById<LinearLayout>(R.id.menuPharmacy).setOnClickListener {
            startActivity(Intent(this, PharmacyActivity::class.java))
            drawerLayout.closeDrawers()
        }
        findViewById<LinearLayout>(R.id.menuDoctors).setOnClickListener {
            startActivity(Intent(this, TopDoctorsActivity::class.java))
            drawerLayout.closeDrawers()
        }
        findViewById<LinearLayout>(R.id.menuAmbulance).setOnClickListener {
            startActivity(Intent(this, AmbulanceActivity::class.java))
            drawerLayout.closeDrawers()
        }
        findViewById<LinearLayout>(R.id.menuLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            drawerLayout.closeDrawers()
        }
        findViewById<LinearLayout>(R.id.menuSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
            drawerLayout.closeDrawers()
        }
        findViewById<LinearLayout>(R.id.menuHelp).setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
            drawerLayout.closeDrawers()
        }
    }
}