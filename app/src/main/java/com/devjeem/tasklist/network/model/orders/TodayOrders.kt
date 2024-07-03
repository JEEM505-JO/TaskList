package com.devjeem.tasklist.network.model.orders


import com.google.gson.annotations.SerializedName


data class TodayOrders(
    @SerializedName("apk_code")
    var apkCode: Int = 228,
    @SerializedName("apk_version")
    var apkVersion: String = "standard",
    @SerializedName("area_id")
    var areaId: Int = 0,
    @SerializedName("tables")
    var tables: Boolean = false,
    @SerializedName("tabs")
    var tabs: Boolean = true
)