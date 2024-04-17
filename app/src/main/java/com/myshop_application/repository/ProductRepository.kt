package com.myshop_application.repository

import com.myshop_application.model.Product
import com.myshop_application.model.ProductResponse
import retrofit2.Response

interface ProductRepository {
    suspend fun getList(token:String, dto : Product) : Response<ProductResponse>
    suspend fun getSearchList(token:String,keyword: String) : Response<ProductResponse>
}