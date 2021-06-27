package com.target.targetcasestudy.data.repository

import androidx.lifecycle.LiveData
import com.target.targetcasestudy.data.AppRepository
import com.target.targetcasestudy.data.entities.Deal
import com.target.targetcasestudy.data.local.DealDao
import com.target.targetcasestudy.data.network.Resource
import com.target.targetcasestudy.data.remote.DealListDataResource
import javax.inject.Inject

class DealRepositoryImpl @Inject constructor(
    private val localDataSource: DealDao,
    private val remoteDataSource: DealListDataResource
): AppRepository(), DealRepository {

    override fun getDeals(): LiveData<Resource<List<Deal>>> = performGetOperation(
        databaseQuery = {localDataSource.getAllDeals()},
        networkCall = {remoteDataSource.getDeals()},
        saveCallResult = {localDataSource.insertAll(it.products)}
    )

    override fun getDeal(id: Int): LiveData<Resource<Deal>> = performGetOperation(
        databaseQuery = {localDataSource.getDeal(id)},
        networkCall = {remoteDataSource.getDeal(id)},
        saveCallResult = {localDataSource.insert(it)}
    )

}