package com.devjeem.tasklist.network.model.license

data class Websockets(
    val blocked_minutes: Int,
    val key: String,
    val type: Type
)