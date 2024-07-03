package com.devjeem.tasklist.network.model.orders

import com.google.gson.annotations.SerializedName

data class ResponseLicense(
    @SerializedName("response")
    var response : String,
    @SerializedName("data")
    var data : Data,
    @SerializedName("error")
    var error : Any? = null


)