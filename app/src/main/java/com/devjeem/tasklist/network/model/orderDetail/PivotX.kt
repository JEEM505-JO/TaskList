package com.devjeem.tasklist.network.model.orderDetail

data class PivotX(
    val group_tax_id: Any,
    val group_tax_name: Any,
    val item_id: Int,
    val tax_id: Int,
    val tax_name: String,
    val total: Double,
    val type: String,
    val value: Double
)