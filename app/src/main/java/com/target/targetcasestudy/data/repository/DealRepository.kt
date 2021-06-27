package com.target.targetcasestudy.data.repository

import androidx.lifecycle.LiveData
import com.target.targetcasestudy.data.entities.Deal
import com.target.targetcasestudy.data.network.Resource

interface DealRepository {

    fun getDeals(): LiveData<Resource<List<Deal>>>

    fun getDeal(id: Int): LiveData<Resource<Deal>>

}