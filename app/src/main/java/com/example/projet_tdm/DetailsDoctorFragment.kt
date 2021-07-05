package com.example.projet_tdm

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.projet_tdm.ui.DemandeConseil
import com.example.projet_tdm.ui.doctors.DoctorViewModel
import com.example.projet_tdm.ui.treatment.TreatmentsViewModel
import kotlinx.android.synthetic.main.fragment_details_doctor.*

class DetailsDoctorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_doctor, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val vm = ViewModelProvider(requireActivity()).get(DoctorViewModel::class.java)
        val doctor= vm.dr

        Glide.with(this).load(url +doctor.imageDoctor)
                .apply(RequestOptions().placeholder(R.drawable.placeholder)).into(imageDoctor)
        nameView.setText("Dr. "+doctor.nameDoctor+" "+doctor.lastNameDoctor)
        specialityView.setText(doctor.speciality)
        exp.setText(doctor.expDoctor.toString())
        biographieContent.setText(doctor.biographieDoctor)

        telephoneDoctor.setOnClickListener(){
            val uri = Uri.parse("tel:"+doctor.phoneDoctor)
            val intent = Intent(Intent.ACTION_DIAL, uri)
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                requireActivity().startActivity(intent)
            }
        }
        localisationDoctor.setOnClickListener(){
            val latitude = doctor.latDoctor
            val longitude = doctor.lngDoctor
            val geoLocation = Uri.parse("http://maps.google.com/maps?daddr="+latitude+","+longitude)
            val intent = Intent(Intent.ACTION_VIEW,geoLocation)
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                requireActivity().startActivity(intent)
            }
        }
        facebookDoctor.setOnClickListener(){
            try{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/"+doctor.fbDoctor))
                requireActivity().startActivity(intent)
            }
            //Application facebook non installé
            catch(e: ActivityNotFoundException){
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://m.facebook.com/"+doctor.fbDoctor)
                    )
                )
            }

        }
        reserver.setOnClickListener(){
            it.findNavController()?.navigate(R.id.action_detailsDoctorFragment_to_navigation_agenda)

        }
        conseil.setOnClickListener(){
            //it.findNavController()?.navigate(R.id.action_detailsDoctorFragment_to_adviceFragment)
            val intent = Intent(requireActivity(),DemandeConseil::class.java)
            intent.putExtra("Dr",doctor)
            this.startActivity(intent)

        }

    }

}