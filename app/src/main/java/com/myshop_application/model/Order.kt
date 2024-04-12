package com.myshop_application.model

data class Order(
    val id: Long,
    val member_id: String,
    val total_price: Long,
    val address: String
)

data class OrderResponse(
    val dto: Order,
    val list: List<Order>,
    val error: String
)