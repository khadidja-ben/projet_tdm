package com.example.projet_tdm.ui.doctors

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
import com.example.projet_tdm.entity.Speciality
import com.example.projet_tdm.entity.Treatment
import com.example.projet_tdm.retrofit.RetrofitService
import com.example.projet_tdm.ui.adapters.DoctorAdapter
import com.example.projet_tdm.ui.adapters.SpecAdapter
import com.example.projet_tdm.ui.adapters.TreatmentAdapter
import kotlinx.android.synthetic.main.fragment_doctors.*
import kotlinx.android.synthetic.main.fragment_treatments.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoctorsFragment : Fragment() {

  lateinit var specViewModel: DoctorViewModel
  lateinit var adapterDoctors: DoctorAdapter
  lateinit var specPrem:Speciality

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_doctors, container, false)
    return root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val vm = ViewModelProvider(requireActivity()).get(DoctorViewModel::class.java)

    adapterDoctors = DoctorAdapter(requireActivity(),vm)
    tasksRecyclerView.layoutManager =
      LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    tasksRecyclerView.adapter = adapterDoctors


    specViewModel = ViewModelProvider(requireActivity()).get(DoctorViewModel::class.java)


    getSpecialities()
  }

  fun getSpecialities(){
    val call = RetrofitService.endpoint.getSpecialities()
    call.enqueue(object : Callback<List<Speciality>> {
      override fun onResponse(call: Call<List<Speciality>>, response: Response<List<Speciality>>) {
        if(response.isSuccessful){
          val list = response.body()
          //val data = mutableListOf<Treatment>()
          if(list != null){
            showSpecialities(list)
            update(list.get(0).specialityId,list.get(0))
            //Toast.makeText(activity, list.get(0).speciality, Toast.LENGTH_SHORT).show()
          }
        } else{
          Toast.makeText(activity, "Une erreur response is not successful!", Toast.LENGTH_SHORT).show()
        }
      }

      override fun onFailure(call: Call<List<Speciality>>, t: Throwable) {
        Toast.makeText(activity, "Une erreur s'est produite onFailure!", Toast.LENGTH_SHORT).show()
      }

    })
  }

  fun getDoctorsBySpeciality(idSpeciality: Int){
    val call = RetrofitService.endpoint.doctorsBySpeciality(idSpeciality)
    call.enqueue(object : Callback<List<Doctor>> {
      override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
        if(response.isSuccessful){
          val list = response.body()
          //val data = mutableListOf<Treatment>()
          if(list != null){
            adapterDoctors.setListDoctors(list)
          }
        } else{
          Toast.makeText(activity, "Une erreur response is not successful!", Toast.LENGTH_SHORT).show()
        }
      }

      override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {
        Toast.makeText(activity, "Une erreur s'est produite onFailure!", Toast.LENGTH_SHORT).show()
      }

    })
  }

  fun showSpecialities(list: List<Speciality>){
    var adapterSpec = SpecAdapter(requireActivity(), this)
    specRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    specRecyclerView.adapter = adapterSpec
    adapterSpec.setListSpec(list)
  }



  fun update(id: Int, spec: Speciality) {
    //tacheViewModel.tache=tache
    Toast.makeText(
      requireContext(), id.toString(),
      Toast.LENGTH_SHORT
    ).show()
    getDoctorsBySpeciality(id)



    /*adapterDoctors.setListDoctors(listOf<Doctor>(
            Doctor("Lilya","Beddek","067798900",1,"Image",12,12,12,"fb","zezezaezaeaeea"),
            Doctor("Lilya","Beddek","067798900",1,"Image",12,12,12,"fb","zezezaezaeaeea"),
            Doctor("Lilya","Beddek","067798900",1,"Image",12,12,12,"fb","zezezaezaeaeea"),
            Doctor("Lilya","Beddek","067798900",1,"Image",12,12,12,"fb","zezezaezaeaeea"),
            ))*/

  }






}