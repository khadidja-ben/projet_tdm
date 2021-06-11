package com.example.projet_tdm.roomdao

import androidx.room.*
import com.example.projet_tdm.entity.Advice

@Dao
interface AdviceDao {

    @Query("select * from advices")
    fun getAdvices():List<Advice>

    @Query("select * from advices where isSynchronized=0")
    fun getAdvicesToSynchronize():List<Advice>


    @Insert
    fun addAdvice(vararg advice: Advice)

    @Update
    fun updateAdvice(advices: List<Advice>)



}


