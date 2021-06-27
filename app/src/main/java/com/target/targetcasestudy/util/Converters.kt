package com.target.targetcasestudy.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.target.targetcasestudy.data.model.Price

class Converters {

    @TypeConverter
    fun priceFromString(value: String?): Price{
        return value?.let {
            Gson().fromJson(value,Price::class.java)
        } ?: run{
            Price(0,"","")
        }

    }

    @TypeConverter
    fun jsonFromPrice(price: Price?): String{
        return price.let {
            Gson().toJson(price)
        } ?: run {
            ""
        }
    }

}