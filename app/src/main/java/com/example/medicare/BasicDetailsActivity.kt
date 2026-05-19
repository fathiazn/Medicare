package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BasicDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_details)

        val etName        = findViewById<EditText>(R.id.etName)
        val etAge         = findViewById<EditText>(R.id.etAge)
        val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
        val etHeight      = findViewById<EditText>(R.id.etHeight)
        val etWeight      = findViewById<EditText>(R.id.etWeight)
        val btnNext       = findViewById<Button>(R.id.btnNext)

        btnNext.setOnClickListener {
            val name   = etName.text.toString().trim()
            val age    = etAge.text.toString().trim()
            val height = etHeight.text.toString().trim()
            val weight = etWeight.text.toString().trim()

            if (name.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("MedicareProfile", MODE_PRIVATE)
            prefs.edit().apply {
                putString("name",   name)
                putString("age",    age)
                putString("gender", spinnerGender.selectedItem.toString())
                putString("height", height)
                putString("weight", weight)
                apply()
            }

            startActivity(Intent(this, MedicalDetailsActivity::class.java))
        }
    }
}