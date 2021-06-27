package com.target.targetcasestudy.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.target.targetcasestudy.data.entities.Deal

@Dao
interface DealDao {

    @Query("SELECT * FROM deal")
    fun getAllDeals(): LiveData<List<Deal>>

    @Query("SELECT * FROM deal WHERE id = :id")
    fun getDeal(id: Int): LiveData<Deal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(deals: List<Deal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(deal: Deal)
}