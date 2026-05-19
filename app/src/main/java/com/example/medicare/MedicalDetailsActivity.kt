package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MedicalDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_details)

        val spinnerBloodGroup = findViewById<Spinner>(R.id.spinnerBloodGroup)
        val etBloodPressure   = findViewById<EditText>(R.id.etBloodPressure)
        val etHeartRate       = findViewById<EditText>(R.id.etHeartRate)
        val etConditions      = findViewById<EditText>(R.id.etConditions)
        val etAllergies       = findViewById<EditText>(R.id.etAllergies)
        val etMedications     = findViewById<EditText>(R.id.etMedications)
        val btnNext           = findViewById<Button>(R.id.btnNext)
        val btnBack           = findViewById<Button>(R.id.btnBack)

        btnNext.setOnClickListener {
            val bloodPressure = etBloodPressure.text.toString().trim()
            val heartRate     = etHeartRate.text.toString().trim()

            if (bloodPressure.isEmpty() || heartRate.isEmpty()) {
                Toast.makeText(this, "Please fill Blood Pressure and Heart Rate", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save to SharedPreferences
            val prefs = getSharedPreferences("MedicareProfile", MODE_PRIVATE)
            prefs.edit().apply {
                putString("bloodGroup",    spinnerBloodGroup.selectedItem.toString())
                putString("bloodPressure", bloodPressure)
                putString("heartRate",     heartRate)
                putString("conditions",    etConditions.text.toString().trim())
                putString("allergies",     etAllergies.text.toString().trim())
                putString("medications",   etMedications.text.toString().trim())
                apply()
            }

            startActivity(Intent(this, LifestyleActivity::class.java))
        }

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}