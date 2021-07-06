package com.example.projet_tdm.roomdao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.projet_tdm.entity.Advice
import com.example.projet_tdm.entity.Treatment

@Database(entities=arrayOf(Advice::class, Treatment::class),version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase:RoomDatabase() {

    abstract fun getAdviceDao():AdviceDao
    abstract fun daoTreatments(): TreatmentDao

}