package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnNext).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        findViewById<CardView>(R.id.cardHospitalRecords).setOnClickListener {
            // Add HospitalRecordsActivity when ready
        }

        // ✅ Settings opens SettingsActivity
        findViewById<CardView>(R.id.cardSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        // ✅ Help opens HelpActivity
        findViewById<CardView>(R.id.cardHelp).setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }

        findViewById<CardView>(R.id.cardProfile).setOnClickListener {
            // Add ProfileActivity when ready
        }
    }
}