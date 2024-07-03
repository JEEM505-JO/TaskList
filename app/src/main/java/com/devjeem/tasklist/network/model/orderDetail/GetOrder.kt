package com.devjeem.tasklist.network.model.orderDetail

data class GetOrder(
    val `data`: Data,
    val error: Any,
    val response: String
)