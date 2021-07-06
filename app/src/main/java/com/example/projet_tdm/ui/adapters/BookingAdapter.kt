package com.example.projet_tdm.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.ui.bookings.BookingsViewModel
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Booking

class BookingAdapter(val context: Context, val vm: BookingsViewModel): RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    private val bookings = mutableListOf<Booking>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.booking_item_layout,
            parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = bookings[position]
        holder.date.text = booking.bookingDate
        holder.heure.text = booking.bookingTime
        holder.medecin.text = "Dr. "+booking.nameDoctor

        holder.itemView.setOnClickListener(){

            vm.bk = bookings[position]
            holder.itemView.findNavController()?.navigate(R.id.action_navigation_agenda_to_infosBookingFragment)

        }
    }

    override fun getItemCount(): Int {
        return bookings.size
    }

    fun setBookings(list: List<Booking>){
        bookings.clear()
        bookings.addAll(list)
        notifyDataSetChanged()
    }

    class BookingViewHolder(view: View): RecyclerView.ViewHolder(view){

        val date: TextView = view.findViewById(R.id.viewDate)
        val heure: TextView = view.findViewById(R.id.viewTime)
        val medecin: TextView = view.findViewById(R.id.viewDoctorName)

    }
}