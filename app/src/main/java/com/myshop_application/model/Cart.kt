package com.myshop_application.model

data class Cart(
    val id : Long? = null,
    val member_id : String? = null,
    val product_id : Long? = null,
    val createdAt : String? = null,
    val product_name : String? = null,
    val product_price : Long? = null,
    val product_brand : String? = null,
    val product_imageUrl : String? = null
)

data class CartResponse(
    val dto : Cart,
    val list : List<Cart>,
    val status : String
)
