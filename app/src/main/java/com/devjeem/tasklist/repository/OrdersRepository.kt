package com.devjeem.tasklist.repository

import com.devjeem.tasklist.network.TaskClient
import com.devjeem.tasklist.network.model.license.LicenseActivate
import com.devjeem.tasklist.network.model.orderDetail.Order
import com.devjeem.tasklist.network.model.orders.TodayOrders
import javax.inject.Inject

class OrdersRepository @Inject constructor(private val api: TaskClient) {

    suspend fun activateLicense() =
        api.activateLicense(LicenseActivate())

    suspend fun getTodayOrders(apikey: String) = api.getTodayOrders(TodayOrders(), apikey)


    suspend fun getOrder(order: Order, apikey: String) = api.getOrder(order, apikey)

}