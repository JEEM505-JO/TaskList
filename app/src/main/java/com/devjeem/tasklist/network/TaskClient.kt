package com.devjeem.tasklist.network

import com.devjeem.tasklist.network.model.TodayOrders
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskClient {

    @POST("orders/today-orders")
    suspend fun getTasks(@Body todayOrders: TodayOrders): Response

}