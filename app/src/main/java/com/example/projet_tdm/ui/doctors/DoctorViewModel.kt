package com.example.projet_tdm.ui.doctors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet_tdm.entity.Doctor

class DoctorViewModel : ViewModel() {

    var dr : Doctor = Doctor(
            0,
            "",
            "",
            "",
            0,
            "",
            0F,
            0F,
            0,
            "",
            "",
            ""
    )

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}