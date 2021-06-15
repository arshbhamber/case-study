package com.target.targetcasestudy.util

import com.google.gson.Gson
import com.target.targetcasestudy.data.model.ErrorData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class AppNetworkCallback<T>: Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {

        when(response.code()){
            200 -> {
                response.body()?.let { onSuccess(it) } ?: run{ onFailed(ErrorData("Api Failed",""))}
            }
            404 -> {
                val errorData = Gson().fromJson(response.errorBody()?.string(), ErrorData::class.java)
                errorData?.let { onFailed(it) } ?: run{ onFailed(ErrorData("Api Failed","")) }
            }
            else -> {
                onFailed(ErrorData("Api Failed",""))
            }
        }

    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailed(ErrorData("Something went wrong",""))
    }

    abstract fun onSuccess(response: T)

    abstract fun onFailed(errorData: ErrorData)


}