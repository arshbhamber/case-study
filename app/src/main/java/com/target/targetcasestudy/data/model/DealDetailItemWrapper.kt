package com.target.targetcasestudy.data.model

data class DealDetailItemWrapper<T>(
    val type: DealDetailItemType,
    val data: T
)

enum class DealDetailItemType{
    DEAL_TOP_VIEW,
    DEAL_TEXT_VIEW
}