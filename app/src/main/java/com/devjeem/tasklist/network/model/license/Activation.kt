package com.devjeem.tasklist.network.model.license

data class Activation(
    val active: Int,
    val api_key: String,
    val bofa: Boolean,
    val code: String,
    val code_number: Int,
    val created_at: String,
    val device_id: String,
    val device_information: String,
    val hostname: String,
    val id: Int,
    val license_id: Int,
    val terminal_id: Any,
    val token: Any,
    val updated_at: String
)