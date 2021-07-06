package com.example.projet_tdm.ui.bookings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Booking
import com.example.projet_tdm.retrofit.RetrofitService
import com.example.projet_tdm.ui.adapters.BookingAdapter
import kotlinx.android.synthetic.main.fragment_bookings.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class BookingsFragment : Fragment() {

    lateinit var vm : BookingsViewModel

    lateinit var bookingAdapter : BookingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_bookings, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm = ViewModelProvider(requireActivity()).get(BookingsViewModel::class.java)
        bookingAdapter = BookingAdapter(requireActivity(), vm)



        val pref = requireActivity().getSharedPreferences("myPrefs", AppCompatActivity.MODE_PRIVATE)
        val id = pref.getInt("idPatient", 1)

        val sdf = SimpleDateFormat("yyyy-M-d")
        var curDate: String=sdf.format(Date())
        Toast.makeText(requireActivity(), curDate, Toast.LENGTH_SHORT).show()
        getBookingTimes(id, curDate)


        calendarBookings.setOnDateChangeListener(CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
            curDate = year.toString() + "-" + (month+1).toString() + "-" + dayOfMonth.toString()
            Toast.makeText(requireActivity(), curDate, Toast.LENGTH_SHORT).show()
            getBookingTimes(1, curDate)
        })
        bookingsRecyclerViewAgenda.adapter = bookingAdapter
        bookingsRecyclerViewAgenda.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

    fun getBookingTimes(idPatient: Int, bookingDate: String){
        val call = RetrofitService.endpoint.getAllBookingPatientByDate(idPatient, bookingDate)
        call.enqueue(object : Callback<List<Booking>> {

            override fun onResponse(call: Call<List<Booking>>, response: Response<List<Booking>>) {
                if(response.isSuccessful){
                    val list = response.body()
                    if(list!=null){
                        bookingAdapter.setBookings(list)
                        Toast.makeText(requireActivity(), list.size.toString(), Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(requireActivity(), "Une erreur response is not successful!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Booking>>, t: Throwable) {
                Toast.makeText(requireActivity(), "Une erreur s'est produite onFailure!", Toast.LENGTH_SHORT).show()
            }
        })
    }



}