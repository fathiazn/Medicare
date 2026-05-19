package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class BookingConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_confirmation)

        // Get details
        val name   = intent.getStringExtra("PATIENT_NAME")  ?: "-"
        val age    = intent.getStringExtra("PATIENT_AGE")   ?: "-"
        val doctor = intent.getStringExtra("DOCTOR_NAME")   ?: "-"
        val phone  = intent.getStringExtra("PATIENT_PHONE") ?: "-"
        val amount = intent.getStringExtra("TOTAL_AMOUNT")  ?: "200"

        // Debug log
        Log.d("BOOKING", "name=$name age=$age doctor=$doctor phone=$phone amount=$amount")

        // Generate appointment number
        val appointmentNumber = "#" + (100000..999999).random()

        // Display details
        findViewById<TextView>(R.id.txtAppointmentNumber).text = appointmentNumber
        findViewById<TextView>(R.id.txtConfirmName).text       = name
        findViewById<TextView>(R.id.txtConfirmAge).text        = age
        findViewById<TextView>(R.id.txtConfirmDoctor).text     = doctor
        findViewById<TextView>(R.id.txtConfirmPhone).text      = phone
        findViewById<TextView>(R.id.txtConfirmAmount).text     = "$$amount.00"

        // Go to Home
        findViewById<Button>(R.id.btnGoHome).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Back also goes to Home
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@BookingConfirmationActivity, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        })
    }
}