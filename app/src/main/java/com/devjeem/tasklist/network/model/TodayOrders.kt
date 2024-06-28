package com.devjeem.tasklist.network.model


import com.google.gson.annotations.SerializedName


data class TodayOrders(
    @SerializedName("apk_code")
    var apkCode: Int,
    @SerializedName("apk_version")
    var apkVersion: String,
    @SerializedName("area_id")
    var areaId: Int,
    @SerializedName("tables")
    var tables: Boolean,
    @SerializedName("tabs")
    var tabs: Boolean
)