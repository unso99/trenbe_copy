package com.myshop_application.service

import com.myshop_application.model.Cart
import com.myshop_application.model.CartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CartService {
    @POST("cart/post-cart")
    suspend fun cartAdd(
        @Header("Authorization") token: String,
        @Body body: Cart
    ): Response<CartResponse>

    @POST("cart/get-carts")
    suspend fun getCarts(
        @Header("Authorization") token: String,
        @Body body: Cart
    ): Response<CartResponse>

    @POST("cart/delete-cart")
    suspend fun deleteCart(
        @Header("Authorization") token: String,
        @Body body: Cart
    ): Response<CartResponse>
}