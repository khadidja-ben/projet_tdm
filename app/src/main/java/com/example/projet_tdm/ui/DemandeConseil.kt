package com.example.projet_tdm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.work.*
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Advice
import com.example.projet_tdm.entity.Doctor
import com.example.projet_tdm.retrofit.RetrofitService
import com.example.projet_tdm.roomdao.RoomService
import com.example.projet_tdm.service.SyncService
import kotlinx.android.synthetic.main.activity_demande_conseil.send
import kotlinx.android.synthetic.main.activity_demande_conseil.conseilText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_medcin_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DemandeConseil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demande_conseil)

        val doctor= intent.getSerializableExtra("Dr") as Doctor

        send.setOnClickListener(){
            val conseil = Advice(1, doctor.idDoctor, conseilText.text.toString())
            RoomService.appDataBase.getAdviceDao().addAdvice(conseil)
            conseilText.text.clear()
            scheduleSycn()
            //addAdvice(conseil)
        }
    }

    private fun scheduleSycn() {
        val constraints = Constraints.Builder().
        setRequiredNetworkType(NetworkType.CONNECTED).
            //    setRequiresBatteryNotLow(true).
        build()
        val req= OneTimeWorkRequest.Builder (SyncService::class.java).
        setConstraints(constraints).addTag("id1").
        build()
        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE,req)

    }

    private fun addAdvice(conseil: Advice) {
        val call = RetrofitService.endpoint.addAdvice(conseil)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        Toast.makeText(this@DemandeConseil, "success", Toast.LENGTH_LONG ).show()
                        this@DemandeConseil.finish()
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    }
                } else {
                    Toast.makeText(this@DemandeConseil, "erreur1", Toast.LENGTH_LONG ).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }
}