package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import com.myshop_application.model.Order
import com.myshop_application.model.OrderResponse
import retrofit2.Response

class OrderRepositoryImpl : OrderRepository {
    override suspend fun getOrderId(token: String): Response<Long> {
        return RetrofitManager.orderService.getOrderId(token)
    }

    override suspend fun addOrders(token: String, dto: Order): Response<OrderResponse> {
        return RetrofitManager.orderService.addOrders(token,dto)
    }
}