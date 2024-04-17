package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import com.myshop_application.model.Product
import com.myshop_application.model.ProductResponse
import retrofit2.Response

class ProductRepositoryImpl : ProductRepository {
    override suspend fun getList(token: String, dto: Product): Response<ProductResponse> {
        return RetrofitManager.productService.getList(token,dto)
    }

    override suspend fun getSearchList(token: String, keyword: String): Response<ProductResponse> {
        return RetrofitManager.productService.getSearchList(token,keyword)
    }
}