package com.example.projet_tdm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "treatments")
data class Treatment (var treatmentBeginDate: Date,
                      var treatmentEndDate: Date,
                      var treatmentDescription: String,
                      var idDoctor: Int,
                      var nameDoctor: String,
                      var lastNameDoctor:String,
                      var idPatient: Int,
                      var disease: String,
                      var medecin: String): Serializable{
                        @PrimaryKey(autoGenerate = true)
                        @ColumnInfo(name = "idTreatment")
                        var idTreatment: Int=0
                    }