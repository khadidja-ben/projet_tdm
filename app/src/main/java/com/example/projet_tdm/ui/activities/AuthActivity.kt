package com.example.projet_tdm.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Patient
import com.example.projet_tdm.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_auth.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        login_button.setOnClickListener(){
            val phone = phone.text.toString()
            val password = password.text.toString()
            login(phone, password)
        }
    }

    fun login(phone: String, password: String){

        val call = RetrofitService.endpoint.authentification(phone, password)
        call.enqueue(object : Callback<Patient> {

            override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                val res = response.body()
                if (response.isSuccessful){
                    if (res!= null){
                        Toast.makeText(this@AuthActivity, "Welcome !", Toast.LENGTH_SHORT).show()
                        val pref = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
                        val editor = pref.edit()
                        editor.putBoolean("connected", true)
                        editor.putInt("idPatient", res.idPatient)
                        editor.putString("namePatient", res.namePatient)
                        editor.putString("lastNamePatient", res.lastNamePatient)
                        editor.putString("phone", res.phonePatient)
                        editor.putString("img", res.imagePatient)
                        editor.commit()
                        val mainActivity = Intent(applicationContext, UserPatientActivity::class.java)
                        startActivity(mainActivity)
                        finish()
                    }
                }
                else{
                    Toast.makeText(this@AuthActivity, "Echec !!!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Patient>, t: Throwable) {
                Toast.makeText(this@AuthActivity, "Echec onFailure !", Toast.LENGTH_SHORT).show()
            }
        })
    }

}