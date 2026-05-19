package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class LifestyleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifestyle)

        val spinnerActivity = findViewById<Spinner>(R.id.spinnerActivity)
        val spinnerSmoking  = findViewById<Spinner>(R.id.spinnerSmoking)
        val spinnerAlcohol  = findViewById<Spinner>(R.id.spinnerAlcohol)
        val etDiet          = findViewById<EditText>(R.id.etDiet)
        val btnBack         = findViewById<Button>(R.id.btnBack)
        val btnFinish       = findViewById<Button>(R.id.btnFinish)

        btnFinish.setOnClickListener {
            val prefs = getSharedPreferences("MedicareProfile", MODE_PRIVATE)
            prefs.edit().apply {
                putString("activityLevel", spinnerActivity.selectedItem.toString())
                putString("smoking",       spinnerSmoking.selectedItem.toString())
                putString("alcohol",       spinnerAlcohol.selectedItem.toString())
                putString("diet",          etDiet.text.toString().trim())
                putBoolean("profileDone",  true)
                apply()
            }
            startActivity(Intent(this, ProfileCreatedActivity::class.java))
            finish()
        }

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}