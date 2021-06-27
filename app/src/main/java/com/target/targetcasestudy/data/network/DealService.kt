package com.target.targetcasestudy.data.network

import com.target.targetcasestudy.data.entities.Deal
import com.target.targetcasestudy.data.model.Deals
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DealService {

    @GET("mobile_case_study_deals/v1/deals")
    suspend fun getListOfDeals(): Response<Deals>

    @GET("mobile_case_study_deals/v1/deals/{id}")
    suspend fun getDealItem(@Path("id") id: Int): Response<Deal>

}