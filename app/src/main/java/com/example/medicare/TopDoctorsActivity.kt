package com.example.medicare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

data class Doctor(
    val name: String,
    val speciality: String,
    val rating: String,
    val distance: String,
    val imageRes: Int
)

class TopDoctorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_doctors)

        window.statusBarColor = getColor(R.color.primary_blue)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        val doctors = listOf(
            Doctor("Dr Rishi",  "Cardiologist", "4.7", "800m", R.drawable.doctor_rishi),
            Doctor("Dr Preetha","Dentist",       "4.7", "800m", R.drawable.doctor_preetha),
            Doctor("Dr Anu",    "Orthopaedic",  "4.7", "800m", R.drawable.doctor_anu),
            Doctor("Dr Seera",  "Cardiologist", "4.7", "800m", R.drawable.doctor_seera)
        )

        val recycler = findViewById<RecyclerView>(R.id.recyclerDoctors)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = DoctorAdapter(doctors) { doctor ->
            val intent = Intent(this, DoctorDetailActivity::class.java)
            intent.putExtra("DOCTOR_NAME", doctor.name)
            intent.putExtra("DOCTOR_SPECIALITY", doctor.speciality)
            intent.putExtra("DOCTOR_RATING", doctor.rating)
            intent.putExtra("DOCTOR_IMAGE", doctor.imageRes)
            startActivity(intent)
            finish() // ✅ Remove TopDoctorsActivity from back stack
        }
    }
}

class DoctorAdapter(
    private val doctors: List<Doctor>,
    private val onClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgDoctor: ImageView = view.findViewById(R.id.imgDoctor)
        val txtName: TextView = view.findViewById(R.id.txtDoctorName)
        val txtSpeciality: TextView = view.findViewById(R.id.txtSpeciality)
        val txtRating: TextView = view.findViewById(R.id.txtRating)
        val txtDistance: TextView = view.findViewById(R.id.txtDistance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.txtName.text = doctor.name
        holder.txtSpeciality.text = doctor.speciality
        holder.txtRating.text = "⭐ ${doctor.rating}"
        holder.txtDistance.text = doctor.distance
        holder.imgDoctor.setImageResource(doctor.imageRes)
        holder.itemView.setOnClickListener { onClick(doctor) }
    }

    override fun getItemCount() = doctors.size
}