package com.devjeem.tasklist.network.model.license

data class Terminal(
    val active: Int,
    val auth_key: String,
    val created_at: String,
    val customer_id: Int,
    val devices_count: Int,
    val id: Int,
    val name: String,
    val print_this: Int,
    val register_id: String,
    val tpn: Any,
    val type: String,
    val updated_at: String,
    val url: Any,
    val venue_id: Int
)