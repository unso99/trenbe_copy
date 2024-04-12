package com.myshop_application.repository

import retrofit2.Response

interface OrderRepository {
    suspend fun getOrderId() : Response<Int>
}