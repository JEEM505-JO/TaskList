package com.devjeem.tasklist.network.model.orderDetail

data class Split(
    val id: Int,
    val order_id: Int,
    val payments: Int,
    val pending: Double,
    val total: Double,
    val total_paid: Double
)