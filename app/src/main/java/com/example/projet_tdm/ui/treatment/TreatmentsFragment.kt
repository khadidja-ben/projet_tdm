package com.example.projet_tdm.ui.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Doctor
import com.example.projet_tdm.entity.Treatment
import com.example.projet_tdm.retrofit.RetrofitService
import com.example.projet_tdm.ui.adapters.TreatmentAdapter
import kotlinx.android.synthetic.main.fragment_treatments.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class TreatmentsFragment : Fragment() {

  companion object {
    fun newInstance() = TreatmentsFragment()
  }

  //lateinit var adapterTreatment: TreatmentAdapter
  lateinit var TreatmentsviewModel: TreatmentsViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_treatments, container, false)
    return root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    TreatmentsviewModel = ViewModelProvider(requireActivity()).get(TreatmentsViewModel::class.java)
    //adapterTreatment = TreatmentAdapter(requireActivity())

    getTreatments(1)
  }

  fun getTreatments(idPatient: Int){
    val call = RetrofitService.endpoint.getCurrentTreatments(idPatient)
    call.enqueue(object : Callback<List<Treatment>>{

      override fun onResponse(call: Call<List<Treatment>>, response: Response<List<Treatment>>) {
        if(response.isSuccessful){
          val list = response.body()
          val data = mutableListOf<Treatment>()
          if(list != null){
            showTreatments(list)
            Toast.makeText(activity, "success!", Toast.LENGTH_LONG).show()
          }
        } else{
          Toast.makeText(activity, "Une erreur response is not successful!", Toast.LENGTH_LONG).show()
        }
      }

      override fun onFailure(call: Call<List<Treatment>>, t: Throwable) {
        Toast.makeText(activity, "Une erreur s'est produite onFailure!", Toast.LENGTH_LONG).show()
      }

    })
  }

  fun showTreatments(list: List<Treatment>){
    treatRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    treatRecyclerView.adapter = TreatmentAdapter(requireActivity(), list)
  }
}

/*
  for (item in list){
              val callDoctor = RetrofitService.endpoint.getDoctor(item.idDoctor)

              callDoctor.enqueue(object : Callback<Doctor>{
                override fun onResponse(call: Call<Doctor>, response: Response<Doctor>){
                  if(response.isSuccessful){
                    val doctor = response.body()
                    data.add(Treatment(
                     item.idTreatment, item.treatmentBeginDate, item.treatmentEndDate, item.treatmentDescription, item.idDoctor, doctor, item.idPatient, item.disease
                    ))
                  }
                }
                override fun onFailure(call: Call<Doctor>, t: Throwable) {
                  Toast.makeText(activity, "Une erreur s'est produite onFailure Doctor!", Toast.LENGTH_LONG).show()
                }
              })
            }
 */