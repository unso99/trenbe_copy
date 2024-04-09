package com.myshop_application.service

import com.myshop_application.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductService {
    @GET("product/get-list")
    suspend fun getList() : Response<ProductResponse>
}