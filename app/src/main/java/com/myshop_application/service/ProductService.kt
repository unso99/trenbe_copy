package com.myshop_application.service

import com.myshop_application.model.Product
import com.myshop_application.model.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductService {
    @POST("product/get-list")
    suspend fun getList(@Body body: Product): Response<ProductResponse>

    @POST("product/get-search-list")
    suspend fun getSearchList(@Body body : String) : Response<ProductResponse>
}