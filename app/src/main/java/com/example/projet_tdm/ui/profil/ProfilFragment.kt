package com.example.projet_tdm.ui.profil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.projet_tdm.R
import com.example.projet_tdm.ui.AuthActivity
import com.example.projet_tdm.ui.UserPatientActivity
import com.example.projet_tdm.ui.adapters.DoctorAdapter
import com.example.projet_tdm.ui.doctors.DoctorViewModel
import kotlinx.android.synthetic.main.fragment_doctors.*
import kotlinx.android.synthetic.main.fragment_profil.*


class ProfilFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_profil, container, false)
    return root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    deconnexion.setOnClickListener(){
      val pref = requireActivity().getSharedPreferences("myPrefs", AppCompatActivity.MODE_PRIVATE)
      val editor = pref.edit()
      editor.putBoolean("connected", false)
      editor.commit()
      val mainActivity = Intent(requireActivity(), AuthActivity::class.java)
      startActivity(mainActivity)
      requireActivity().finish()
    }
  }

}