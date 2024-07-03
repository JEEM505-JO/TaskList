package com.devjeem.tasklist.network.model.license

import com.google.gson.annotations.SerializedName

data class ResponseActivate(
    @SerializedName("data")
    val data: Data,
    @SerializedName("error")
    val error: Boolean? = null,
    @SerializedName("message")
    val response: String
)