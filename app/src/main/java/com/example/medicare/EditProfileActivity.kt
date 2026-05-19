package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val prefs = getSharedPreferences("MedicareProfile", MODE_PRIVATE)

        // Basic info views
        val etName           = findViewById<EditText>(R.id.etName)
        val etAge            = findViewById<EditText>(R.id.etAge)
        val spinnerGender    = findViewById<Spinner>(R.id.spinnerGender)
        val etHeight         = findViewById<EditText>(R.id.etHeight)
        val etWeight         = findViewById<EditText>(R.id.etWeight)

        // Medical info views
        val spinnerBloodGroup = findViewById<Spinner>(R.id.spinnerBloodGroup)
        val etBloodPressure   = findViewById<EditText>(R.id.etBloodPressure)
        val etHeartRate       = findViewById<EditText>(R.id.etHeartRate)
        val etConditions      = findViewById<EditText>(R.id.etConditions)
        val etAllergies       = findViewById<EditText>(R.id.etAllergies)
        val etMedications     = findViewById<EditText>(R.id.etMedications)

        // Lifestyle views
        val spinnerActivity  = findViewById<Spinner>(R.id.spinnerActivity)
        val spinnerSmoking   = findViewById<Spinner>(R.id.spinnerSmoking)
        val spinnerAlcohol   = findViewById<Spinner>(R.id.spinnerAlcohol)
        val etDiet           = findViewById<EditText>(R.id.etDiet)

        val btnSave          = findViewById<Button>(R.id.btnSave)
        val btnBack          = findViewById<Button>(R.id.btnBack)

        // Pre-fill all fields from SharedPreferences
        etName.setText(prefs.getString("name", ""))
        etAge.setText(prefs.getString("age", ""))
        etHeight.setText(prefs.getString("height", ""))
        etWeight.setText(prefs.getString("weight", ""))
        etBloodPressure.setText(prefs.getString("bloodPressure", ""))
        etHeartRate.setText(prefs.getString("heartRate", ""))
        etConditions.setText(prefs.getString("conditions", ""))
        etAllergies.setText(prefs.getString("allergies", ""))
        etMedications.setText(prefs.getString("medications", ""))
        etDiet.setText(prefs.getString("diet", ""))

        // Set spinner selections
        setSpinner(spinnerGender,     resources.getStringArray(R.array.gender_options),      prefs.getString("gender", ""))
        setSpinner(spinnerBloodGroup, resources.getStringArray(R.array.blood_group_options), prefs.getString("bloodGroup", ""))
        setSpinner(spinnerActivity,   resources.getStringArray(R.array.activity_options),    prefs.getString("activityLevel", ""))
        setSpinner(spinnerSmoking,    resources.getStringArray(R.array.smoking_options),     prefs.getString("smoking", ""))
        setSpinner(spinnerAlcohol,    resources.getStringArray(R.array.alcohol_options),     prefs.getString("alcohol", ""))

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val age  = etAge.text.toString().trim()

            if (name.isEmpty() || age.isEmpty()) {
                Toast.makeText(this, "Name and Age are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            prefs.edit().apply {
                putString("name",          name)
                putString("age",           age)
                putString("gender",        spinnerGender.selectedItem.toString())
                putString("height",        etHeight.text.toString().trim())
                putString("weight",        etWeight.text.toString().trim())
                putString("bloodGroup",    spinnerBloodGroup.selectedItem.toString())
                putString("bloodPressure", etBloodPressure.text.toString().trim())
                putString("heartRate",     etHeartRate.text.toString().trim())
                putString("conditions",    etConditions.text.toString().trim())
                putString("allergies",     etAllergies.text.toString().trim())
                putString("medications",   etMedications.text.toString().trim())
                putString("activityLevel", spinnerActivity.selectedItem.toString())
                putString("smoking",       spinnerSmoking.selectedItem.toString())
                putString("alcohol",       spinnerAlcohol.selectedItem.toString())
                putString("diet",          etDiet.text.toString().trim())
                apply()
            }

            Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    // Helper to select correct spinner item
    private fun setSpinner(spinner: Spinner, items: Array<String>, value: String?) {
        val index = items.indexOfFirst { it == value }
        if (index >= 0) spinner.setSelection(index)
    }
}