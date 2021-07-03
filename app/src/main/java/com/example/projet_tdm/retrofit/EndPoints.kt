package com.example.projet_tdm.retrofit


import com.example.projet_tdm.entity.Advice
import com.example.projet_tdm.entity.Doctor
import com.example.projet_tdm.entity.Treatment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface EndPoints {

    @GET("doctors")
    fun getDoctors():Call<List<Doctor>>

    @POST("addAdvice")
    fun addAdvice (@Body advice: Advice):Call<String>

    @POST ("addAdvices")
    fun addAdvices (@Body advices: List<Advice>):Call<String>

    @GET("currentTreatmentsPatient/{id}")
    fun getCurrentTreatments(@Path("id") idPatient: Int):Call<List<Treatment>>

    @GET("doctor/{id}")
    fun getDoctor(@Path("id") idDoctor: Int):Call<Doctor>
}