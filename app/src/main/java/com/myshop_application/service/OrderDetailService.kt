package com.myshop_application.service

import com.myshop_application.model.OrderDetail
import com.myshop_application.model.OrderDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderDetailService {
    @POST("orderDetail/post-orderDetail")
    suspend fun addOrderDetail(
        @Header("Authorization") token: String,
        @Body body: OrderDetail
    ): Response<OrderDetailResponse>
}