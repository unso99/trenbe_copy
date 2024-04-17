package com.myshop_application.service

import com.myshop_application.model.Product
import com.myshop_application.model.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ProductService {
    @POST("product/get-list")
    suspend fun getList(
        @Header("Authorization") token: String,
        @Body body: Product
    ): Response<ProductResponse>

    @POST("product/get-search-list")
    suspend fun getSearchList(
        @Header("Authorization") token: String,
        @Body body: String
    ): Response<ProductResponse>
}