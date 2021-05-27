package com.example.projet_tdm.ui.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is bookings Fragment"
    }
    val text: LiveData<String> = _text
}