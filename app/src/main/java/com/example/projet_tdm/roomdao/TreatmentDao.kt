package com.example.projet_tdm.roomdao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projet_tdm.entity.Treatment

@Dao
interface TreatmentDao {
    @Query("select * from treatments")
    fun getTreatments():LiveData<List<Treatment>>

    @Query("DELETE FROM treatments")
    fun clearTreatments()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTreatment(treatment: Treatment)


}