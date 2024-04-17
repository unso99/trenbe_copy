package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import com.myshop_application.model.Cart
import com.myshop_application.model.CartResponse
import retrofit2.Response

class CartRepositoryImpl : CartRepository {
    override suspend fun addCart(token: String, dto: Cart): Response<CartResponse> {
        return RetrofitManager.cartService.cartAdd(token,dto)
    }

    override suspend fun getCarts(token: String, dto: Cart): Response<CartResponse> {
        return RetrofitManager.cartService.getCarts(token,dto)
    }

    override suspend fun deleteCart(token: String, dto: Cart): Response<CartResponse> {
        return RetrofitManager.cartService.deleteCart(token,dto)
    }
}