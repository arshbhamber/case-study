package com.target.targetcasestudy.data.remote

import com.target.targetcasestudy.data.network.DealService
import javax.inject.Inject

class DealListDataResource @Inject constructor(
    private val dealService: DealService
): BaseDataResource() {

    suspend fun getDeals() = getResult { dealService.getListOfDeals() }

    suspend fun getDeal(id: Int) = getResult { dealService.getDealItem(id) }

}