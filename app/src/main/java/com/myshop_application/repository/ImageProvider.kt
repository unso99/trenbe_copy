package com.myshop_application.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.myshop_application.RetrofitManager
import com.myshop_application.model.Image
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class ImageProvider(private val callback: Callback) {

    fun getImage(imageUrl : String) {
        val dto = Image(imageUrl = imageUrl)
        RetrofitManager.imageService.getImage(dto)
            .enqueue(object : retrofit2.Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                        callback.getImage(bitmap)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }

            })
    }

    interface Callback{
        fun getImage(bitmap: Bitmap)
    }
}