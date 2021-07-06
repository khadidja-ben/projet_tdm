package com.example.projet_tdm.retrofit


import com.example.projet_tdm.entity.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface EndPoints {

    @GET("doctors")
    fun getDoctors():Call<List<Doctor>>

    @POST("advice")
    fun addAdvice (@Body advice: Advice):Call<String>

    @POST ("advices")
    fun addAdvices (@Body advices: List<Advice>):Call<String>

    @GET("currentTreatmentsPatient/{id}")
    fun getCurrentTreatments(@Path("id") idPatient: Int):Call<List<Treatment>>

    @GET("doctor/{id}")
    fun getDoctor(@Path("id") idDoctor: Int):Call<Doctor>

    @GET("/patientAuth/{phone}/{password}")
    fun authentification(@Path ("phone") phone:String, @Path("password")password: String): Call<Patient>

    @GET("/speciality")
    fun getSpecialities():Call<List<Speciality>>

    @GET("doctorsBySpeciality/{idSpeciality}")
    fun doctorsBySpeciality(@Path("idSpeciality") idSpeciality: Int):Call<List<Doctor>>

    @GET("/bookingTime/{bookingDate}")
    fun bookingTimes(@Path("bookingDate") bookingDate: String):Call<List<BookingTime>>

    @GET("/booking/{bookingDate}/{bookingTime}/{idPatient}/{idDoctor}")
    fun addBooking(@Path("bookingDate") bookingDate: String, @Path("bookingTime")bookingTime: String,@Path("idPatient") idPatient: Int, @Path("idDoctor")idDoctor: Int):Call<String>

    @GET("/bookingPatient/{idPatient}/{bookingDate}")
    fun getAllBookingPatientByDate(@Path("idPatient") idPatient: Int, @Path("bookingDate") bookingDate: String): Call <List<Booking>>

}