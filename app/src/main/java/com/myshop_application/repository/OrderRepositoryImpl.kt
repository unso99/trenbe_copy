package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import retrofit2.Response

class OrderRepositoryImpl : OrderRepository {
    override suspend fun getOrderId(): Response<Int> {
        return RetrofitManager.orderService.getOrderId()
    }
}