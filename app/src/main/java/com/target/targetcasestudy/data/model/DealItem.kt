package com.target.targetcasestudy.data.model

data class DealItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val aisle: String,
    val image_url: String?,
    val regular_price: Price?,
    val sale_price: Price?
)