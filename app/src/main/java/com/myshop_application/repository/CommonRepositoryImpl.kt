package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import com.myshop_application.model.Common
import com.myshop_application.model.CommonResponse
import retrofit2.Response

class CommonRepositoryImpl : CommonRepository {
    override suspend fun getCommonList(token: String, body: Common): Response<CommonResponse> {
        return RetrofitManager.commonService.getCommonList(token,body)
    }
}