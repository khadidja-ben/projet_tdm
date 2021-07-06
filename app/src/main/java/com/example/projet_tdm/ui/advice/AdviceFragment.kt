package com.example.projet_tdm.ui.advice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Advice
import com.example.projet_tdm.roomdao.RoomService
import com.example.projet_tdm.service.SyncService
import com.example.projet_tdm.ui.doctors.DoctorViewModel
import kotlinx.android.synthetic.main.fragment_advice.*

class AdviceFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advice, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val vm = ViewModelProvider(requireActivity()).get(DoctorViewModel::class.java)
        val doctor= vm.dr

        send.setOnClickListener(){

            val pref = requireActivity().getSharedPreferences("myPrefs", AppCompatActivity.MODE_PRIVATE)
            val id = pref.getInt("idPatient", 1)

            val conseil = Advice(id, doctor.idDoctor, conseilText.text.toString(),0)
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
        val workManager = WorkManager.getInstance(requireActivity())
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE,req)

    }

}