package com.example.medicare

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var imgProfile: ImageView
    private lateinit var tvInitial: TextView
    private var photoUri: Uri? = null

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            contentResolver.takePersistableUriPermission(
                it, Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            photoUri = it
            imgProfile.setImageURI(it)
            imgProfile.scaleType = ImageView.ScaleType.CENTER_CROP
            tvInitial.visibility = View.GONE
            getSharedPreferences("MedicareProfile", MODE_PRIVATE)
                .edit().putString("photoUri", it.toString()).apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        imgProfile = findViewById(R.id.imgProfile)
        tvInitial  = findViewById(R.id.tvInitial)

        // Load saved photo if exists
        val savedUri = getSharedPreferences("MedicareProfile", MODE_PRIVATE)
            .getString("photoUri", null)
        if (savedUri != null) {
            imgProfile.setImageURI(Uri.parse(savedUri))
            imgProfile.scaleType = ImageView.ScaleType.CENTER_CROP
            tvInitial.visibility = View.GONE
        }

        // Tap photo to change
        imgProfile.setOnClickListener {
            pickImage.launch("image/*")
        }

        loadProfile()

        // Back button — returns to HomeActivity
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnEditProfile).setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        findViewById<Button>(R.id.btnDeleteProfile).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete your account? This cannot be undone.")
                .setPositiveButton("Delete") { _, _ ->
                    getSharedPreferences("MedicareProfile", MODE_PRIVATE)
                        .edit().clear().apply()
                    startActivity(Intent(this, RegisterActivity::class.java))
                    finishAffinity()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        loadProfile()
    }

    private fun loadProfile() {
        val prefs = getSharedPreferences("MedicareProfile", MODE_PRIVATE)

        val name        = prefs.getString("name", "N/A") ?: "N/A"
        val nameInitial = name.firstOrNull()?.uppercase() ?: "?"

        val savedUri = prefs.getString("photoUri", null)
        if (savedUri != null) {
            imgProfile.setImageURI(Uri.parse(savedUri))
            imgProfile.scaleType = ImageView.ScaleType.CENTER_CROP
            tvInitial.visibility = View.GONE
        } else {
            tvInitial.visibility = View.VISIBLE
            tvInitial.text       = nameInitial
        }

        findViewById<TextView>(R.id.tvName).text        = name
        findViewById<TextView>(R.id.tvAge).text         = prefs.getString("age", "N/A") ?: "N/A"
        findViewById<TextView>(R.id.tvGender).text      = prefs.getString("gender", "N/A") ?: "N/A"
        findViewById<TextView>(R.id.tvHeight).text      = (prefs.getString("height", "N/A") ?: "N/A") + " cm"
        findViewById<TextView>(R.id.tvWeight).text      = (prefs.getString("weight", "N/A") ?: "N/A") + " kg"
        findViewById<TextView>(R.id.tvBloodGroup).text  = prefs.getString("bloodGroup", "N/A") ?: "N/A"
        findViewById<TextView>(R.id.tvBP).text          = prefs.getString("bloodPressure", "N/A") ?: "N/A"
        findViewById<TextView>(R.id.tvHeartRate).text   = prefs.getString("heartRate", "N/A") ?: "N/A"
        findViewById<TextView>(R.id.tvConditions).text  = (prefs.getString("conditions", "None") ?: "None").ifEmpty { "None" }
        findViewById<TextView>(R.id.tvAllergies).text   = (prefs.getString("allergies", "None") ?: "None").ifEmpty { "None" }
        findViewById<TextView>(R.id.tvMedications).text = (prefs.getString("medications", "None") ?: "None").ifEmpty { "None" }
        findViewById<TextView>(R.id.tvActivity).text    = prefs.getString("activityLevel", "N/A") ?: "N/A"
        findViewById<TextView>(R.id.tvSmoking).text     = prefs.getString("smoking", "N/A") ?: "N/A"
        findViewById<TextView>(R.id.tvAlcohol).text     = prefs.getString("alcohol", "N/A") ?: "N/A"
        findViewById<TextView>(R.id.tvDiet).text        = (prefs.getString("diet", "None") ?: "None").ifEmpty { "None" }
    }
}