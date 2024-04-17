package com.myshop_application.service

import com.myshop_application.model.Image
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ImageService {
    @POST("image/get-images")
    fun getImage(@Header("Authorization") token: String, @Body body: Image): Call<ResponseBody>
}