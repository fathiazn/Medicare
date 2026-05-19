package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DoctorDetailActivity : AppCompatActivity() {

    private var isExpanded = false
    private var selectedDateIndex = 1
    private var selectedTimeIndex = 1

    val shortAbout = "Dr. Rishi is a highly experienced cardiologist specializing in the diagnosis, treatment, and prevention of heart-related diseases."
    val fullAbout = "Dr. Rishi is a highly experienced cardiologist specializing in the diagnosis, treatment, and prevention of heart-related diseases. With over 15 years of experience, he has treated thousands of patients and is known for his compassionate care. He completed his MBBS from Colombo Medical Faculty and MD in Cardiology from the National Hospital. He is available Monday to Saturday and offers both in-person and online consultations."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_detail)

        window.statusBarColor = getColor(R.color.primary_blue)

        val name = intent.getStringExtra("DOCTOR_NAME") ?: "Dr Rishi"
        val speciality = intent.getStringExtra("DOCTOR_SPECIALITY") ?: "Cardiologist"
        val rating = intent.getStringExtra("DOCTOR_RATING") ?: "4.7"
        val imageRes = intent.getIntExtra("DOCTOR_IMAGE", R.drawable.doctor_rishi)

        findViewById<TextView>(R.id.txtDoctorName).text = name
        findViewById<TextView>(R.id.txtSpeciality).text = speciality
        findViewById<TextView>(R.id.txtRating).text = "⭐ $rating"
        findViewById<ImageView>(R.id.imgDoctor).setImageResource(imageRes)

        // Read more
        val txtAbout = findViewById<TextView>(R.id.txtAbout)
        val txtReadMore = findViewById<TextView>(R.id.txtReadMore)
        txtAbout.text = shortAbout
        txtReadMore.setOnClickListener {
            if (isExpanded) {
                txtAbout.text = shortAbout
                txtReadMore.text = "Read more..."
            } else {
                txtAbout.text = fullAbout
                txtReadMore.text = "Read less"
            }
            isExpanded = !isExpanded
        }

        // Selectable dates
        val dateLayouts = listOf<LinearLayout>(
            findViewById(R.id.date1),
            findViewById(R.id.date2),
            findViewById(R.id.date3),
            findViewById(R.id.date4)
        )
        val numIds = listOf(R.id.dateNum1, R.id.dateNum2, R.id.dateNum3, R.id.dateNum4)
        val dayIds = listOf(R.id.dateDay1, R.id.dateDay2, R.id.dateDay3, R.id.dateDay4)

        updateDateSelection(dateLayouts, numIds, dayIds, selectedDateIndex)
        dateLayouts.forEachIndexed { index, layout ->
            layout.setOnClickListener {
                selectedDateIndex = index
                updateDateSelection(dateLayouts, numIds, dayIds, index)
            }
        }

        // Selectable times
        val timeSlots = listOf<TextView>(
            findViewById(R.id.time1),
            findViewById(R.id.time2),
            findViewById(R.id.time3)
        )
        updateTimeSelection(timeSlots, selectedTimeIndex)
        timeSlots.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                selectedTimeIndex = index
                updateTimeSelection(timeSlots, index)
            }
        }

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener { finish() }

        // ✅ finish() added so DoctorDetailActivity is removed from back stack
        findViewById<Button>(R.id.btnBookNow).setOnClickListener {
            val intent = Intent(this, AppointmentActivity::class.java)
            intent.putExtra("DOCTOR_NAME", name)
            startActivity(intent)
            finish()
        }
    }

    private fun updateDateSelection(
        layouts: List<LinearLayout>,
        numIds: List<Int>,
        dayIds: List<Int>,
        selected: Int
    ) {
        layouts.forEachIndexed { index, layout ->
            if (index == selected) {
                layout.setBackgroundColor(getColor(R.color.primary_blue))
                layout.findViewById<TextView>(numIds[index]).setTextColor(getColor(R.color.white))
                layout.findViewById<TextView>(dayIds[index]).setTextColor(getColor(R.color.white))
            } else {
                layout.setBackgroundColor(getColor(R.color.white))
                layout.findViewById<TextView>(numIds[index]).setTextColor(getColor(R.color.text_dark))
                layout.findViewById<TextView>(dayIds[index]).setTextColor(getColor(R.color.text_grey))
            }
        }
    }

    private fun updateTimeSelection(slots: List<TextView>, selected: Int) {
        slots.forEachIndexed { index, tv ->
            if (index == selected) {
                tv.setBackgroundColor(getColor(R.color.primary_blue))
                tv.setTextColor(getColor(R.color.white))
            } else {
                tv.setBackgroundColor(getColor(R.color.white))
                tv.setTextColor(getColor(R.color.text_dark))
            }
        }
    }
}