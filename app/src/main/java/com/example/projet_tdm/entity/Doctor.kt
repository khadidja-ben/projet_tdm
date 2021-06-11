package com.example.projet_tdm.entity

import java.io.Serializable

data class Doctor(val idDoctor: Int,
                  val name:String,
                  val lastName:String,
                  val phone: String,
                  val speciality: String,
                  val image:String,
                  val lat: Int,
                  val lng: Int,
                  val exp : Int,
                  val fb : String) :Serializable