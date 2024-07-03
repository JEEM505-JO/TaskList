package com.devjeem.tasklist.network.model.license

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("close-device")
    val closeDevice: String,
    @SerializedName("sync-device")
    val syncDevice: String
)