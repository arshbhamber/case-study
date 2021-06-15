package com.target.targetcasestudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.*
import com.target.targetcasestudy.data.model.*
import com.target.targetcasestudy.data.network.Resource
import com.target.targetcasestudy.util.AppNetworkCallback
import com.target.targetcasestudy.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val dealListLiveData: MutableLiveData<Resource<List<DealItem>>> = MutableLiveData()
    val selectedDeal: SingleLiveEvent<DealItem> = SingleLiveEvent()
    val dealLiveData: MutableLiveData<Resource<DealItem>> = MutableLiveData()

    init {
        fetchListOfDeals()
    }

    private fun fetchListOfDeals() {

        dealListLiveData.postValue(Resource.loading())

        val callback = object : AppNetworkCallback<Deals>() {

            override fun onSuccess(response: Deals) {
                dealListLiveData.postValue(Resource.success(response.products))
            }

            override fun onFailed(errorData: ErrorData) {
                dealListLiveData.postValue(Resource.error(errorData.message))

            }

        }

        repository.fetchListOfDeals(callback)

    }


    fun fetchDealItem(item: DealItem) {

        dealLiveData.postValue(Resource.loading())

        val callback = object : AppNetworkCallback<DealItem>() {

            override fun onSuccess(response: DealItem) {
                dealLiveData.postValue(Resource.success(response))
            }

            override fun onFailed(errorData: ErrorData) {
                dealLiveData.postValue(Resource.error(errorData.message))
            }

        }

        repository.fetchDealItem(item.id, callback)

    }

    fun getWrapperList(item: DealItem): List<DealDetailItemWrapper<*>> {

        val list = mutableListOf<DealDetailItemWrapper<*>>()

        list.add(DealDetailItemWrapper(DealDetailItemType.DEAL_TOP_VIEW, item))

        split(item.description, 2000).forEach {
            list.add(
                DealDetailItemWrapper(
                    DealDetailItemType.DEAL_TEXT_VIEW, it
                )
            )
        }

        return list

    }

    private fun split(text: String, limit: Int): List<String> {

        val list = mutableListOf<String>()

        var start = 0

        while (start < text.length) {
            list.add(text.substring(start, text.length.coerceAtMost(start + limit)))
            start += limit
        }

        return list

    }


}