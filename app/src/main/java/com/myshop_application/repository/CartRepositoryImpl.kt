package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import com.myshop_application.model.Cart
import com.myshop_application.model.CartResponse
import retrofit2.Response

class CartRepositoryImpl : CartRepository {
    override suspend fun addCart(dto: Cart): Response<CartResponse> {
        return RetrofitManager.cartService.cartAdd(dto)
    }

    override suspend fun getCarts(dto: Cart): Response<CartResponse> {
        return RetrofitManager.cartService.getCarts(dto)
    }

    override suspend fun deleteCart(dto: Cart): Response<CartResponse> {
        return RetrofitManager.cartService.deleteCart(dto)
    }
}