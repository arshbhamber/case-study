package com.target.targetcasestudy

import com.target.targetcasestudy.repositories.MockAppRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainViewModelTest{

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup(){
        viewModel = MainViewModel(MockAppRepository())
    }

    @Test
    fun insertDealItem(){

    }

}