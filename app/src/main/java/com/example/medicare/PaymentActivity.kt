package com.example.medicare

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class PaymentActivity : AppCompatActivity() {

    private var selectedMethod = "VISA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val txtTotal = findViewById<TextView>(R.id.txtTotalAmount)
        txtTotal.text = "TOTAL AMOUNT - $${String.format("%.2f", CartManager.getTotal())}"

        val btnVisa   = findViewById<TextView>(R.id.btnVisa)
        val btnMaster = findViewById<TextView>(R.id.btnMaster)
        val btnBank   = findViewById<TextView>(R.id.btnBank)
        val imgCard   = findViewById<ImageView>(R.id.imgSelectedCard)
        val etExpiry  = findViewById<EditText>(R.id.etExpiry)

        fun selectMethod(method: String) {
            selectedMethod = method
            btnVisa.alpha   = if (method == "VISA") 1f else 0.4f
            btnMaster.alpha = if (method == "MC")   1f else 0.4f
            btnBank.alpha   = if (method == "BANK") 1f else 0.4f

            when (method) {
                "VISA" -> imgCard.setImageResource(R.drawable.ic_visa)
                "MC"   -> imgCard.setImageResource(R.drawable.ic_mastercard)
                "BANK" -> imgCard.setImageResource(R.drawable.ic_bank)
            }
        }

        btnVisa.setOnClickListener   { selectMethod("VISA") }
        btnMaster.setOnClickListener { selectMethod("MC")   }
        btnBank.setOnClickListener   { selectMethod("BANK") }

        // Expiry date picker
        etExpiry.isFocusable = false
        etExpiry.isClickable = true
        etExpiry.setOnClickListener {
            val cal = Calendar.getInstance()
            val dialog = DatePickerDialog(
                this,
                { _, year, month, _ ->
                    val mm = String.format("%02d", month + 1)
                    val yy = year.toString().takeLast(2)
                    etExpiry.setText("$mm/$yy")
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                1
            )
            dialog.datePicker.minDate = System.currentTimeMillis()
            dialog.show()

            try {
                val daySpinner = dialog.datePicker
                    .findViewById<View>(
                        resources.getIdentifier("day", "id", "android")
                    )
                daySpinner?.visibility = View.GONE
            } catch (_: Exception) {}
        }

        // Submit
        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val cardName   = findViewById<EditText>(R.id.etCardName).text.toString()
            val cardNumber = findViewById<EditText>(R.id.etCardNumber).text.toString()
            val expiry     = etExpiry.text.toString()
            val cvc        = findViewById<EditText>(R.id.etCvc).text.toString()

            if (cardName.isEmpty() || cardNumber.length < 16 || expiry.isEmpty() || cvc.length < 3) {
                Toast.makeText(this, "Please enter valid card details", Toast.LENGTH_SHORT).show()
            } else {
                CartManager.clearCart()
                val successIntent = Intent(this, PaymentSuccessActivity::class.java)
                successIntent.putExtra("PATIENT_NAME",  intent.getStringExtra("PATIENT_NAME"))
                successIntent.putExtra("PATIENT_AGE",   intent.getStringExtra("PATIENT_AGE"))
                successIntent.putExtra("PATIENT_PHONE", intent.getStringExtra("PATIENT_PHONE"))
                successIntent.putExtra("DOCTOR_NAME",   intent.getStringExtra("DOCTOR_NAME"))
                successIntent.putExtra("TOTAL_AMOUNT",  intent.getStringExtra("TOTAL_AMOUNT"))
                successIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // ✅ Fixed
                startActivity(successIntent)
                finish()
            }
        }
    }
}