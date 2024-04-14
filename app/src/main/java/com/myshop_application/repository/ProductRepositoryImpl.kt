package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import com.myshop_application.model.Product
import com.myshop_application.model.ProductResponse
import retrofit2.Response

class ProductRepositoryImpl : ProductRepository {
    override suspend fun getList(dto: Product): Response<ProductResponse> {
        return RetrofitManager.productService.getList(dto)
    }

    override suspend fun getSearchList(keyword: String): Response<ProductResponse> {
        return RetrofitManager.productService.getSearchList(keyword)
    }
}