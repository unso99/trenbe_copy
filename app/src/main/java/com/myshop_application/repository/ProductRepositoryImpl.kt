package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import com.myshop_application.model.ProductResponse
import retrofit2.Response

class ProductRepositoryImpl : ProductRepository {
    override suspend fun getList(): Response<ProductResponse> {
        return RetrofitManager.productService.getList()
    }
}