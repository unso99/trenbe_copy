package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import com.myshop_application.model.Order
import com.myshop_application.model.OrderResponse
import retrofit2.Response

class OrderRepositoryImpl : OrderRepository {
    override suspend fun getOrderId(): Response<Long> {
        return RetrofitManager.orderService.getOrderId()
    }

    override suspend fun addOrders(dto: Order): Response<OrderResponse> {
        return RetrofitManager.orderService.addOrders(dto)
    }
}