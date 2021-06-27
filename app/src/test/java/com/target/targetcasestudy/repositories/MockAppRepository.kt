package com.target.targetcasestudy.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.target.targetcasestudy.data.StaticData
import com.target.targetcasestudy.data.entities.Deal
import com.target.targetcasestudy.data.repository.DealRepository
import com.target.targetcasestudy.data.model.Deals
import com.target.targetcasestudy.data.network.Resource
import retrofit2.Callback

class MockAppRepository: DealRepository {

    val dealList = StaticData.deals

    override fun getDeals(): LiveData<Resource<List<Deal>>> {
        return liveData {
          emit(Resource.loading())
          Thread.sleep(2000)
          emit(Resource.success(dealList))

        }
    }

    override fun getDeal(id: Int): LiveData<Resource<Deal>> {
        TODO("Not yet implemented")
    }


}