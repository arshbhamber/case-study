package com.target.targetcasestudy.data.entities

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.target.targetcasestudy.data.model.Price

@Entity(tableName = "deal")
data class Deal(

    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: String?,
    val aisle: String,
    val image_url: String?,
    val regular_price: Price?,
    val sale_price: Price?

)
