package com.example.projet_tdm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.CalendarView.OnDateChangeListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projet_tdm.entity.BookingTime
import com.example.projet_tdm.entity.Doctor
import com.example.projet_tdm.entity.Treatment
import com.example.projet_tdm.retrofit.RetrofitService
import com.example.projet_tdm.ui.doctors.DoctorViewModel
import kotlinx.android.synthetic.main.fragment_book_appointment.*
import kotlinx.android.synthetic.main.fragment_details_doctor.*
import kotlinx.android.synthetic.main.treatment_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import android.R as t
import com.example.projet_tdm.R as r


class BookAppointmentFragment : Fragment() {


    val arraySpinner = arrayOf(
        "09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"
    )
    lateinit var adapter: ArrayAdapter<String>
    var result= mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(r.layout.fragment_book_appointment, container, false)
    }

    fun getBookingTimes(bookingDate: String){
        val call = RetrofitService.endpoint.bookingTimes(bookingDate)
        call.enqueue(object : Callback<List<BookingTime>> {

            override fun onResponse(call: Call<List<BookingTime>>, response: Response<List<BookingTime>>) {
                if(response.isSuccessful){
                    val list = response.body()
                    //val data = mutableListOf<Treatment>()
                    if(list != null){
                        val temp = mutableListOf<String>()
                        result= mutableListOf<String>()
                        for (bookingTime in list){
                            temp.add(bookingTime.bookingTime)
                        }
                        for (heure in arraySpinner){
                            if(!temp.contains(heure)){
                               result.add(heure)
                            }
                        }
                        adapter = ArrayAdapter<String>(
                            requireActivity(),
                            t.layout.simple_spinner_item, result.toTypedArray()
                        )
                        adapter.setDropDownViewResource(t.layout.simple_spinner_dropdown_item)
                        bookingHeure.adapter = adapter
                        Toast.makeText(activity, temp.toString(), Toast.LENGTH_SHORT).show()
                    }
                } else{
                    Toast.makeText(activity, "Une erreur response is not successful!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<BookingTime>>, t: Throwable) {
                Toast.makeText(activity, "Une erreur s'est produite onFailure!", Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun addBooking(bookingDate: String, bookingTime: String, idPatient: Int, idDoctor: Int){
        val call = RetrofitService.endpoint.addBooking(bookingDate, bookingTime, idPatient, idDoctor)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    Toast.makeText(activity, "added successfully", Toast.LENGTH_SHORT).show()
                }  else{
                    Toast.makeText(activity, "Une erreur response is not successful!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(activity, "Une erreur s'est produite onFailure!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            val vm = ViewModelProvider(requireActivity()).get(DoctorViewModel::class.java)
            val doctor= vm.dr

        var curDate: String=""
        var curHeure: String=""
        dateBooking.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
            curDate = year.toString()+"-"+month.toString()+"-"+dayOfMonth.toString()
            Toast.makeText(activity,curDate, Toast.LENGTH_SHORT).show()
            getBookingTimes(curDate)
        })

        adapter = ArrayAdapter<String>(
            requireActivity(),
            t.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(t.layout.simple_spinner_dropdown_item)
        bookingHeure.adapter = adapter

        bookingHeure.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                arg0: AdapterView<*>?,
                arg1: View,
                position: Int,
                id: Long
            ) {

                if (result.size !=0){
                    curHeure=result.get(position)
                    Toast.makeText(requireActivity(), result.get(position), Toast.LENGTH_SHORT)
                        .show()
                }else{
                    curHeure=arraySpinner.get(position)
                    Toast.makeText(requireActivity(), arraySpinner.get(position), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        })

        reserverBooking.setOnClickListener(){
            addBooking(curDate, curHeure, 1, doctor.idDoctor)
            Toast.makeText(requireActivity(), curDate+curHeure, Toast.LENGTH_SHORT).show()
        }


    }

}