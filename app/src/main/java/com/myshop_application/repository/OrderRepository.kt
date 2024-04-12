package com.myshop_application.repository

import com.myshop_application.model.Order
import com.myshop_application.model.OrderResponse
import retrofit2.Response

interface OrderRepository {
    suspend fun getOrderId() : Response<Long>

    suspend fun addOrders(dto : Order) : Response<OrderResponse>
}