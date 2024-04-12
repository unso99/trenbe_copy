package com.myshop_application.service

import retrofit2.Response
import retrofit2.http.GET

interface OrderService {
    @GET("order/get-order-id")
    suspend fun getOrderId() : Response<Int>
}