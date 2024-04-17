package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import com.myshop_application.model.OrderDetail
import com.myshop_application.model.OrderDetailResponse
import retrofit2.Response

class OrderDetailRepositoryImpl : OrderDetailRepository {
    override suspend fun addOrderDetail(
        token: String,
        body: OrderDetail
    ): Response<OrderDetailResponse> {
        return RetrofitManager.orderDetailService.addOrderDetail(token,body)
    }
}