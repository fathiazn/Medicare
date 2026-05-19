package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // ✅ Button (not ImageButton) - matches your layout
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<TextView>(R.id.menuPayment).setOnClickListener { }
        findViewById<TextView>(R.id.menuAbout).setOnClickListener { }
        findViewById<TextView>(R.id.menuNotification).setOnClickListener { }
        findViewById<TextView>(R.id.menuShare).setOnClickListener { }
        findViewById<TextView>(R.id.menuSignOut).setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finishAffinity()
        }
        findViewById<TextView>(R.id.menuLegal).setOnClickListener { }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}