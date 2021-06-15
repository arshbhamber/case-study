package com.target.targetcasestudy.data.network

import com.target.targetcasestudy.data.model.DealItem
import com.target.targetcasestudy.data.model.Deals
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("mobile_case_study_deals/v1/deals")
    fun getListOfDeals(): Call<Deals>

    @GET("mobile_case_study_deals/v1/deals/{id}")
    fun getDealItem(@Path("id") id: Int): Call<DealItem>

}