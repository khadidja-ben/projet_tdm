package com.example.projet_tdm.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Speciality
import com.example.projet_tdm.ui.doctors.DoctorsFragment


class SpecAdapter(val context: Context, frag: DoctorsFragment) :
    RecyclerView.Adapter<ViewHolderSpec>() {
    var fragment = frag
    var data = listOf<Speciality>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSpec {
        return ViewHolderSpec(
            LayoutInflater.from(context)
                .inflate(R.layout.speciality_layout, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolderSpec, position: Int) {
        holder.titreSpec.text = data[position].speciality
        holder.itemView.setOnClickListener(View.OnClickListener {
            fragment.update(data[position].specialityId, data[position])
        })


    }

    fun setListSpec(list: List<Speciality>) {
        data = list
        notifyDataSetChanged()

    }


}

class ViewHolderSpec(view: View) : RecyclerView.ViewHolder(view) {
    val titreSpec = view.findViewById<TextView>(R.id.nomSpec)
}