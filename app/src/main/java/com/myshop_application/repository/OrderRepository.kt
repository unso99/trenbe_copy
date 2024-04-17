package com.myshop_application.repository

import com.myshop_application.model.Order
import com.myshop_application.model.OrderResponse
import retrofit2.Response

interface OrderRepository {
    suspend fun getOrderId(token:String) : Response<Long>

    suspend fun addOrders(token:String,dto : Order) : Response<OrderResponse>
}