package com.myshop_application.service

import com.myshop_application.model.OrderDetail
import com.myshop_application.model.OrderDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderDetailService {
    @POST("orderDetail/post-orderDetail")
    suspend fun addOrderDetail(@Body body: OrderDetail): Response<OrderDetailResponse>
}