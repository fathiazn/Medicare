package com.example.medicare

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AmbulanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ambulance)

        window.statusBarColor = getColor(R.color.primary_blue)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val name = findViewById<EditText>(R.id.etName).text.toString()
            val contact = findViewById<EditText>(R.id.etContact).text.toString()
            val location = findViewById<EditText>(R.id.etLocation).text.toString()
            val patients = findViewById<EditText>(R.id.etPatients).text.toString()

            if (name.isEmpty() || contact.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ambulance requested!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}