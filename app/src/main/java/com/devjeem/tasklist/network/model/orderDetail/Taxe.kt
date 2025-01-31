package com.devjeem.tasklist.network.model.orderDetail

data class Taxe(
    val active: Int,
    val code: String,
    val created_at: String,
    val default: Int,
    val deleted_at: Any,
    val id: Int,
    val name: String,
    val pivot: PivotX,
    val print: Any,
    val type: String,
    val updated_at: String,
    val value: Double,
    val venue_id: Int
)