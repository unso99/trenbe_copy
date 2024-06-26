package com.myshop_application.service

import com.myshop_application.model.Order
import com.myshop_application.model.OrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderService {
    @GET("order/get-order-id")
    suspend fun getOrderId(@Header("Authorization") token: String): Response<Long>

    @POST("order/post-order")
    suspend fun addOrders(
        @Header("Authorization") token: String,
        @Body body: Order
    ): Response<OrderResponse>

}