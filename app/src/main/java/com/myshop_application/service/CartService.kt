package com.myshop_application.service

import com.myshop_application.model.Cart
import com.myshop_application.model.CartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CartService {
    @POST("cart/post-cart")
    suspend fun cartAdd(@Body body: Cart): Response<CartResponse>

    @POST("cart/get-carts")
    suspend fun getCarts(@Body body: Cart): Response<CartResponse>

    @POST("cart/delete-cart")
    suspend fun deleteCart(@Body body: Cart): Response<CartResponse>
}