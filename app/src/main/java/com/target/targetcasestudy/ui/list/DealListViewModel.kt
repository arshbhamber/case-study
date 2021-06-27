package com.target.targetcasestudy.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.entities.Deal
import com.target.targetcasestudy.data.network.Resource
import com.target.targetcasestudy.data.repository.DealRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DealListViewModel @Inject constructor(
    repository: DealRepositoryImpl
) : ViewModel() {

    val dealListLiveData: LiveData<Resource<List<Deal>>> = repository.getDeals()

}