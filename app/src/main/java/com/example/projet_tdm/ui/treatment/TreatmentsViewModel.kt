package com.example.projet_tdm.ui.treatment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet_tdm.entity.Treatment
import java.util.*

class TreatmentsViewModel : ViewModel() {

    var tr: Treatment = Treatment(
        Date(),
        Date(),
        "",
        0,
        "",
        "",
        0,
        "",
        ""
    )

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title
    fun updateActionBarTitle(title: String) = _title.postValue(title)

}