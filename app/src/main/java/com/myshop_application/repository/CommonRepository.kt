package com.myshop_application.repository

import com.myshop_application.model.Common
import com.myshop_application.model.CommonResponse
import retrofit2.Response

interface CommonRepository {
    suspend fun getCommonList(body: Common): Response<CommonResponse>
}