package com.example.projet_tdm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "specialities")
data class Speciality(var speciality: String
                     ) :Serializable {
                         @PrimaryKey(autoGenerate = true)
                         @ColumnInfo(name = "speciality_id")
                         var specialityId: Int=0
                     }