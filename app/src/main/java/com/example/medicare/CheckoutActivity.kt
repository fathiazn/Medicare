package com.example.medicare

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {

    private val paymentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            showSuccessDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val etFullName    = findViewById<EditText>(R.id.etFullName)
        val etPhone       = findViewById<EditText>(R.id.etPhone)
        val etAddress     = findViewById<EditText>(R.id.etAddress)
        val etCity        = findViewById<EditText>(R.id.etCity)
        val radioPayment  = findViewById<RadioGroup>(R.id.radioPayment)
        val btnPlaceOrder = findViewById<Button>(R.id.btnPlaceOrder)
        val btnBack       = findViewById<ImageButton>(R.id.btnBack)
        val txtSubtotal   = findViewById<TextView>(R.id.txtCheckoutSubtotal)
        val txtTotal      = findViewById<TextView>(R.id.txtCheckoutTotal)

        val subtotal = CartManager.getTotal()
        val total = subtotal + 2.50
        txtSubtotal.text = "$ %.2f".format(subtotal)
        txtTotal.text    = "$ %.2f".format(total)

        btnBack.setOnClickListener { finish() }

        btnPlaceOrder.setOnClickListener {
            val name    = etFullName.text.toString().trim()
            val phone   = etPhone.text.toString().trim()
            val address = etAddress.text.toString().trim()
            val city    = etCity.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (address.isEmpty()) {
                Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (city.isEmpty()) {
                Toast.makeText(this, "Please enter your city", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            when (radioPayment.checkedRadioButtonId) {
                R.id.radioCard -> {
                    paymentLauncher.launch(Intent(this, PaymentActivity::class.java))
                }
                R.id.radioCash, R.id.radioWallet -> {
                    showSuccessDialog()
                }
            }
        }

        // Bottom Navigation
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        findViewById<LinearLayout>(R.id.navNotification).setOnClickListener { }
        findViewById<LinearLayout>(R.id.navProfile).setOnClickListener { }
        findViewById<LinearLayout>(R.id.navReport).setOnClickListener { }
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle("Payment Successful")
            .setMessage("Your order has been placed successfully!")
            .setCancelable(false)
            .setPositiveButton("Track Order") { _, _ ->
                CartManager.clearCart()
                val intent = Intent(this, OrderTrackingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
            .show()
    }
}