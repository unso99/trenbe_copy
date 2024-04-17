package com.myshop_application.repository

import com.myshop_application.model.OrderDetail
import com.myshop_application.model.OrderDetailResponse
import retrofit2.Response

interface OrderDetailRepository {
    suspend fun addOrderDetail(token:String,body: OrderDetail): Response<OrderDetailResponse>
}