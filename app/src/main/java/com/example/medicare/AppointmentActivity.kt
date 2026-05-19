package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        window.statusBarColor = getColor(android.R.color.black)

        val doctorName = intent.getStringExtra("DOCTOR_NAME") ?: ""
        findViewById<EditText>(R.id.etDoctorName).setText(doctorName)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnConfirm).setOnClickListener {
            val name   = findViewById<EditText>(R.id.etName).text.toString()
            val age    = findViewById<EditText>(R.id.etAge).text.toString()
            val phone  = findViewById<EditText>(R.id.etPhone).text.toString()
            val doctor = findViewById<EditText>(R.id.etDoctorName).text.toString()

            if (name.isEmpty() || age.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putExtra("TOTAL_AMOUNT", "200")
                intent.putExtra("PATIENT_NAME", name)
                intent.putExtra("PATIENT_AGE", age)
                intent.putExtra("PATIENT_PHONE", phone)
                intent.putExtra("DOCTOR_NAME", doctor)
                startActivity(intent)
                finish() // ✅ Remove AppointmentActivity from back stack
            }
        }

        findViewById<Button>(R.id.btnCancel).setOnClickListener {
            finish()
        }
    }
}