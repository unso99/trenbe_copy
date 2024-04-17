package com.myshop_application.service

import com.myshop_application.model.Common
import com.myshop_application.model.CommonResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CommonService {

    @POST("common/get-common-list")
    suspend fun getCommonList(
        @Header("Authorization") token: String,
        @Body body: Common
    ): Response<CommonResponse>
}