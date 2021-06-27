package com.target.targetcasestudy

import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.repository.DealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DealRepository
) : ViewModel() {

//    val dealListLiveData: MutableLiveData<Resource<List<DealItem>>> = MutableLiveData()
//    val selectedDeal: SingleLiveEvent<DealItem> = SingleLiveEvent()
//    val dealLiveData: MutableLiveData<Resource<DealItem>> = MutableLiveData()


//    private suspend fun fetchListOfDeals() {
//
//        dealListLiveData.postValue(Resource.loading())
//
//        dealListLiveData.postValue(repository.fetchListOfDeals())
//
//    }


//    suspend fun fetchDealItem(item: DealItem) {
//
//        dealLiveData.postValue(Resource.loading())
//
//        dealLiveData.postValue(repository.fetchDealItem(item.id))
//
//    }

//    fun getWrapperList(item: DealItem): List<DealDetailItemWrapper<*>> {
//
//        val list = mutableListOf<DealDetailItemWrapper<*>>()
//
//        list.add(DealDetailItemWrapper(DealDetailItemType.DEAL_TOP_VIEW, item))
//
//        split(item.description, 2000).forEach {
//            list.add(
//                DealDetailItemWrapper(
//                    DealDetailItemType.DEAL_TEXT_VIEW, it
//                )
//            )
//        }
//
//        return list
//
//    }

//    private fun split(text: String, limit: Int): List<String> {
//
//        val list = mutableListOf<String>()
//
//        var start = 0
//
//        while (start < text.length) {
//            list.add(text.substring(start, text.length.coerceAtMost(start + limit)))
//            start += limit
//        }
//
//        return list
//
//    }


}