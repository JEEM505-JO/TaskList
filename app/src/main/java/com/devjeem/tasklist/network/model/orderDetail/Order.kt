package com.devjeem.tasklist.network.model.orderDetail

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("order_id")
    var orderId : String
)
