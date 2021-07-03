package com.example.projet_tdm.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Treatment
import com.example.projet_tdm.ui.treatment.treatment_details

class TreatmentAdapter (val context: Context, val treatmentsList: List<Treatment>):
    RecyclerView.Adapter<TreatmentViewHolder>() {

    //var treatmentsList = listOf<Treatment>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreatmentViewHolder {
        return TreatmentViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.treatment_layout, parent, false)
        )

    }

    override fun getItemCount() = treatmentsList.size

    override fun onBindViewHolder(holder: TreatmentViewHolder, position: Int) {

        holder.maladie.text = treatmentsList[position].disease
        holder.dateDebut.text = treatmentsList[position].treatmentBeginDate.toString()
        holder.dateFin.text = treatmentsList[position].treatmentEndDate.toString()
        holder.doctor.text = treatmentsList[position].doctor

        /*holder.itemView.setOnClickListener(View.OnClickListener{
            val intent = Intent(context, treatments_details::class.java)
            intent.putExtra("Dr",treatmentsList[position])
            context.startActivity(intent)
        })*/

    }

    /*fun setListTreatments(list: List<Treatment>) {
        treatmentsList = list
        notifyDataSetChanged()
    }*/
}

class TreatmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val maladie = view.findViewById<TextView>(R.id.maladieView)
    val dateDebut = view.findViewById<TextView>(R.id.dateDebutView)
    val dateFin = view.findViewById<TextView>(R.id.dateFinView)
    val doctor = view.findViewById<TextView>(R.id.doctorView)
}