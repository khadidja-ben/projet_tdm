package com.example.projet_tdm.ui.profil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.projet_tdm.R
import com.example.projet_tdm.ui.activities.AuthActivity
import com.example.projet_tdm.url
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
    val pref = requireActivity().getSharedPreferences("myPrefs", AppCompatActivity.MODE_PRIVATE)



    profile_nom.setText(pref.getString("lastNamePatient", ""))
    profile_phone_num.setText(pref.getString("phone", ""))
    profile_prenom.setText(pref.getString("namePatient", ""))

    Glide.with(this).load(url +pref.getString("img", ""))
      .apply(
        RequestOptions().placeholder(R.drawable.placeholder
        ))
      .into(user_image)


    deconnexion.setOnClickListener(){
      val editor = pref.edit()
      editor.putBoolean("connected", false)



      editor.commit()
      val mainActivity = Intent(requireActivity(), AuthActivity::class.java)
      startActivity(mainActivity)
      requireActivity().finish()
    }
  }

}