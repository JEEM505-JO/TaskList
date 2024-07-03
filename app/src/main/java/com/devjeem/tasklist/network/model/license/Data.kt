package com.devjeem.tasklist.network.model.license

data class Data(
    val activation: Activation,
    val customer: Customer,
    val langs: List<Lang>,
    val magensa_keys: Any,
    val terminals: List<Terminal>,
    val venue: Venue,
    val websockets: Websockets
)