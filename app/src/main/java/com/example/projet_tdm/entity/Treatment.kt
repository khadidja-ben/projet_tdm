package com.example.projet_tdm.entity

import java.io.Serializable
import java.util.*

data class Treatment(val idTreatment: Int,
                     val treatmentBeginDate: Date,
                     val treatmentEndDate: Date,
                     val treatmentDescription: String,
                     val idDoctor : Int,
                     val doctor : String,
                     val idPatient: Int,
                     val disease : String) :Serializable