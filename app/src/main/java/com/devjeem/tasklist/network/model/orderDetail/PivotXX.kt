package com.devjeem.tasklist.network.model.orderDetail

data class PivotXX(
    val amount_pay: String,
    val apk_code: String,
    val apk_version: String,
    val item_id: Int,
    val order_payment_id: Int,
    val quantity: Int,
    val weighable: Int
)