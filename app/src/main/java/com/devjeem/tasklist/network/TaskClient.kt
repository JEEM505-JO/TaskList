package com.devjeem.tasklist.network

import com.devjeem.tasklist.network.model.license.LicenseActivate
import com.devjeem.tasklist.network.model.license.ResponseActivate
import com.devjeem.tasklist.network.model.orderDetail.GetOrder
import com.devjeem.tasklist.network.model.orderDetail.Order
import com.devjeem.tasklist.network.model.orders.ResponseLicense
import com.devjeem.tasklist.network.model.orders.TodayOrders
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TaskClient {


    @POST("license/activate")
    suspend fun activateLicense(@Body licenseActivate: LicenseActivate): Response<ResponseActivate>

    @Headers("Content-Type: application/json", "device-id:device_id")
    @POST("orders/today-orders")
    suspend fun getTodayOrders(
        @Body todayOrders: TodayOrders,
        @Header(
            "api-key",
        ) apiKey: String
    ): Response<ResponseLicense>


    @Headers("Content-Type: application/json", "device-id:device_id")
    @POST("orders/get-order")
    suspend fun getOrder(
        @Body order: Order,
        @Header(
            "api-key",
        ) apiKey: String
    ): Response<GetOrder>



}