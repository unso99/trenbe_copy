package com.myshop_application.repository

import com.myshop_application.model.Cart
import com.myshop_application.model.CartResponse
import retrofit2.Response

interface CartRepository {
    suspend fun addCart(dto: Cart): Response<CartResponse>

    suspend fun getCarts(dto: Cart): Response<CartResponse>

    suspend fun deleteCart(dto: Cart): Response<CartResponse>
}