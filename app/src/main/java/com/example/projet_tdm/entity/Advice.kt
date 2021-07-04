package com.example.projet_tdm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "advices")
data class Advice(var idPatient:Int,
                  var idDoctor:Int,
                  var advice: String,
                  var isSynchronized: Int=0
                  ) :Serializable {
                      @PrimaryKey(autoGenerate = true)
                      @ColumnInfo(name = "advice_id")
                      var idAdvice: Int=0
                  }