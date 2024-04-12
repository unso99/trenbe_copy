package com.myshop_application

import com.google.gson.Gson
import com.myshop_application.service.CartService
import com.myshop_application.service.CommonService
import com.myshop_application.service.ImageService
import com.myshop_application.service.MemberService
import com.myshop_application.service.OrderDetailService
import com.myshop_application.service.OrderService
import com.myshop_application.service.ProductService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    const val BASE_URL = "http://192.168.45.202:9000/myShop/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val gson = Gson().newBuilder().setLenient().create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    val memberService: MemberService by lazy { retrofit.create(MemberService::class.java) }

    val productService: ProductService by lazy { retrofit.create(ProductService::class.java) }

    val imageService: ImageService by lazy { retrofit.create(ImageService::class.java) }

    val commonService: CommonService by lazy { retrofit.create(CommonService::class.java) }

    val cartService: CartService by lazy { retrofit.create(CartService::class.java) }

    val orderService: OrderService by lazy { retrofit.create(OrderService::class.java) }

    val orderDetailService: OrderDetailService by lazy { retrofit.create(OrderDetailService::class.java) }
}