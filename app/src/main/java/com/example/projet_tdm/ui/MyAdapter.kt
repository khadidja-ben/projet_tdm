package com.example.projet_tdm.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Doctor
import com.example.projet_tdm.url

class MyAdapter(val context: Context,var data:List<Doctor>):RecyclerView.Adapter<MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.doctor_layout, parent, false))

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = data[position].nameDoctor
        holder.lastName.text = data[position].lastNameDoctor
        holder.phone.text = data[position].phoneDoctor

        Glide.with(context).load(url+data[position].imageDoctor)
            .apply(RequestOptions().placeholder(R.drawable.placeholder
            ))
            .into(holder.img)

        //holder.img.setImageResource(data[position].image)

        holder.phone.setOnClickListener(View.OnClickListener{
            val uri = Uri.parse("tel:021212121")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        })

        /*holder.localisation.setOnClickListener(View.OnClickListener{
            val latitude = data[position].lat
            val longitude = data[position].lng
            val geoLocation = Uri.parse("geo:$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW,geoLocation)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        })*/

        holder.itemView.setOnClickListener(View.OnClickListener{
            val intent = Intent(context, medcinDetails::class.java)
            intent.putExtra("Dr",data[position])
            context.startActivity(intent)
        })

    }
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val img = view.findViewById<ImageView>(R.id.doctorImage)
    val name = view.findViewById<TextView>(R.id.viewName)
    val lastName = view.findViewById<TextView>(R.id.viewLastName)
    val phone = view.findViewById<TextView>(R.id.viewPhone)


}

