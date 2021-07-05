package com.example.projet_tdm.entity

import java.io.Serializable
import java.sql.Time
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Booking")
data class Booking(var bookingTime:String,
                   var bookingDate: Date,
                   var idDoctor:Int,
                   var idTreatment:Int,
                   var idPatient:Int,
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idBooking")
    var idBooking: Int?=null
}