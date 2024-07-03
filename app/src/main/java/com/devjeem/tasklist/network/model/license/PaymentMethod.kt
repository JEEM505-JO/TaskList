package com.devjeem.tasklist.network.model.license

data class PaymentMethod(
    val active: Int,
    val created_at: String,
    val display_name: String,
    val id: Int,
    val locale_currency: Int,
    val name: String,
    val skip_net_sales: Int,
    val updated_at: String,
    val venue_id: Int
)