package com.example.projet_tdm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Doctor
import com.example.projet_tdm.url
import kotlinx.android.synthetic.main.activity_medcin_details.*

class medcinDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medcin_details)

        /*val doctor= intent.getSerializableExtra("Dr") as Doctor

        val img = findViewById<ImageView>(R.id.imageView3)
        val nom = findViewById<TextView>(R.id.nameView)
        val prenom = findViewById<TextView>(R.id.prenomView)
        val phone = findViewById<TextView>(R.id.PhoneView)
        val speciality = findViewById<TextView>(R.id.specialityView)
        val fb = findViewById<TextView>(R.id.facebook)
        val exp = findViewById<TextView>(R.id.exp)

        //img.setImageResource(doctor.image)
        Glide.with(this).load(url +doctor.imageDoctor)
            .apply(RequestOptions().placeholder(R.drawable.placeholder)).into(img)
        nom.setText(doctor.nameDoctor)
        prenom.setText(doctor.lastNameDoctor)
        phone.setText(doctor.phoneDoctor)
        speciality.setText(doctor.specialityId)
        fb.setText(doctor.fbDoctor)
        exp.setText(doctor.expDoctor.toString())*/

        conseil.setOnClickListener(){
            val intent = Intent(this, DemandeConseil::class.java)
           // intent.putExtra("Dr",doctor)
            //this.startActivity(intent)
        }
    }
}