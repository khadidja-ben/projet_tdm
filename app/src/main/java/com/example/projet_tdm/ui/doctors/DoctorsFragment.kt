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
import com.example.projet_tdm.ui.adapters.DoctorAdapter
import com.example.projet_tdm.ui.adapters.SpecAdapter
import kotlinx.android.synthetic.main.fragment_doctors.*

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


    var adapterSpec = SpecAdapter(requireActivity(), this)

    specRecyclerView.layoutManager =
      LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    specRecyclerView.adapter = adapterSpec

    adapterDoctors = DoctorAdapter(requireActivity())
    tasksRecyclerView.layoutManager =
      LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    tasksRecyclerView.adapter = adapterDoctors


    specViewModel = ViewModelProvider(requireActivity()).get(DoctorViewModel::class.java)

    adapterSpec.setListSpec(listOf<Speciality>(
      Speciality(1,"Cardiologie"),
      Speciality(2,"Cardiologie"),
      Speciality(3,"Cardiologie"),
      Speciality(4,"Cardiologie")
    ))
    loadDoctors()




  }

  fun update(id: Int, tache: Speciality) {
    //tacheViewModel.tache=tache
    Toast.makeText(
      requireContext(), id.toString(),
      Toast.LENGTH_SHORT
    ).show()

    adapterDoctors.setListDoctors(listOf<Doctor>(
      Doctor(1,"Lilya","Beddek","0000",1,"Image",12,12,12,"fb"),
      Doctor(2,"Lilya","Beddek","0000",1,"Image",12,12,12,"fb"),
      Doctor(3,"Lilya","Beddek","0000",1,"Image",12,12,12,"fb"),
      Doctor(4,"Lilya","Beddek","0000",1,"Image",12,12,12,"fb"),
      Doctor(5,"Lilya","Beddek","0000",1,"Image",12,12,12,"fb"),

    ))

  }

  fun loadDoctors() {
    adapterDoctors.setListDoctors(listOf<Doctor>(
      Doctor(1,"Katia","Beddek","0000",1,"Image",12,12,12,"fb"),
      Doctor(2,"Katia","Beddek","0000",1,"Image",12,12,12,"fb"),
      Doctor(3,"Katia","Beddek","0000",1,"Image",12,12,12,"fb"),
      Doctor(4,"Katia","Beddek","0000",1,"Image",12,12,12,"fb"),
      Doctor(5,"Katia","Beddek","0000",1,"Image",12,12,12,"fb"),

      ))
  }





}