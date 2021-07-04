package com.example.projet_tdm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "patients")
data class Patient (var phonePatient: String,
                    var idAuthPatient: Int,
                    var password: String,
                    var namePatient: String,
                    var lastNamePatient: String,
                    var agePatient: Int,
                    var imagePatient: String) {
                        @PrimaryKey(autoGenerate = true)
                        @ColumnInfo(name = "idPatient")
                        var idPatient: Int=0
                    }