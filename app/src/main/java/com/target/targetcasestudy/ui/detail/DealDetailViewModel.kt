package com.target.targetcasestudy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.repository.DealRepository
import com.target.targetcasestudy.data.entities.Deal
import com.target.targetcasestudy.data.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DealDetailViewModel @Inject constructor(
    private val repository: DealRepository
) : ViewModel() {

    lateinit var dealLiveData: LiveData<Resource<Deal>>


    fun getDeal(id: Int){
        dealLiveData = repository.getDeal(id)
    }
}