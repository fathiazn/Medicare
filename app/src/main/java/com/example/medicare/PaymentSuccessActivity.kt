package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PaymentSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)

        window.statusBarColor = getColor(R.color.primary_blue)

        // ✅ View Booking Confirmation
        findViewById<Button>(R.id.btnViewBooking).setOnClickListener {
            val confirmIntent = Intent(this, BookingConfirmationActivity::class.java)
            confirmIntent.putExtra("PATIENT_NAME",  intent.getStringExtra("PATIENT_NAME") ?: "-")
            confirmIntent.putExtra("PATIENT_AGE",   intent.getStringExtra("PATIENT_AGE") ?: "-")
            confirmIntent.putExtra("PATIENT_PHONE", intent.getStringExtra("PATIENT_PHONE") ?: "-")
            confirmIntent.putExtra("DOCTOR_NAME",   intent.getStringExtra("DOCTOR_NAME") ?: "-")
            confirmIntent.putExtra("TOTAL_AMOUNT",  intent.getStringExtra("TOTAL_AMOUNT") ?: "200")
            // ✅ No flags — just navigate forward normally
            startActivity(confirmIntent)
            finish()
        }

        // ✅ Back to Home
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}