package com.myshop_application.repository

import com.myshop_application.model.ProductResponse
import retrofit2.Response

interface ProductRepository {
    suspend fun getList() : Response<ProductResponse>
}