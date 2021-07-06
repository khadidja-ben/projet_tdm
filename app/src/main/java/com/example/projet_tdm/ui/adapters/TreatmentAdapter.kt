package com.example.projet_tdm.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Treatment
import com.example.projet_tdm.ui.treatment.TreatmentsViewModel
import java.text.SimpleDateFormat

class TreatmentAdapter (val context: Context, val treatmentsList: List<Treatment>, val vm: TreatmentsViewModel):
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

        val sdf = SimpleDateFormat("yyyy-M-d")
        var dateDebut: String=sdf.format(treatmentsList[position].treatmentBeginDate)
        var dateFin: String=sdf.format(treatmentsList[position].treatmentEndDate)

        holder.dateDebut.text = dateDebut
        holder.dateFin.text = dateFin
        holder.doctor.text = "Dr. "+treatmentsList[position].nameDoctor
        holder.medicament.text = treatmentsList[position].medecin

        holder.itemView.setOnClickListener(View.OnClickListener{
            vm.tr = treatmentsList[position]
            //holder.itemView.findNavController()?.navigate(R.id.action_navigation_dashboard_to_treatment_details)

        })
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
    val medicament = view.findViewById<TextView>(R.id.medicament)
}
