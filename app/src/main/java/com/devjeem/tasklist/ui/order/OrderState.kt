package com.devjeem.tasklist.ui.order

import com.devjeem.tasklist.network.model.orders.GuestOrder

data class OrderState(
    val loading: Boolean = false,
    val error: String = "",
    val smsProgress : String = "Cargando...",
    val isError: Boolean = false,
    val success: Boolean = false,
    val apiKey: String = "",
    val guest_orders: List<GuestOrder> = emptyList(),
    val onOrderDetailNav: (id: String) -> Unit = { },
    val setOrderDetailNav: ((id: String) -> Unit) -> Unit = {},
)

