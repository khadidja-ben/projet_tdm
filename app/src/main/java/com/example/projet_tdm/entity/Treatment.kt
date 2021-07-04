package com.example.projet_tdm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "treatments")
data class Treatment(var treatmentBeginDate: Date,
                     var treatmentEndDate: Date,
                     var treatmentDescription: String,
                     var idDoctor : Int,
                     var doctor : String,
                     var idPatient: Int,
                     var disease : String) :Serializable{
                        @PrimaryKey(autoGenerate = true)
                        @ColumnInfo(name = "treatment_id")
                        var idTreatment: Int=0
                     }