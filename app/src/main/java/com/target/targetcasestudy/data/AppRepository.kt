package com.target.targetcasestudy.data

import com.target.targetcasestudy.data.model.DealItem
import com.target.targetcasestudy.data.model.Deals
import com.target.targetcasestudy.data.network.ApiService
import retrofit2.Callback
import javax.inject.Inject


class AppRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun fetchListOfDeals(callback: Callback<Deals>){
        return apiService.getListOfDeals().enqueue(callback)
    }

    fun fetchDealItem(id: Int, callback: Callback<DealItem>){
        return apiService.getDealItem(id).enqueue(callback)
    }

}