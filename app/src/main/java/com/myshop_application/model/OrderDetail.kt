package com.myshop_application.model

data class OrderDetail(
    val id: Long = 0,
    val orders_id: Long,
    val product_id: Long,
    val created_at: String = ""
)

data class OrderDetailResponse(
    val dto : OrderDetail,
    val list : List<OrderDetail>,
    val error : String
)
