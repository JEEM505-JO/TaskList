package com.devjeem.tasklist.ui.orderDetail

import com.devjeem.tasklist.network.model.orderDetail.Data
import com.devjeem.tasklist.network.model.orderDetail.GetOrder

data class OrderDetailState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val order: Data? = null,
    val success: Boolean = false,
    val initData : (id: String) -> Unit = { },
    val onNavOrderList : () -> Unit = {},
    val setOnNavOrderList : (() -> Unit) -> Unit = {}

    )
