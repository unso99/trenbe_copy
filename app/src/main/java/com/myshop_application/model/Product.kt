package com.myshop_application.model

data class Product(
    val id : Long,
    val name : String,
    val price : Long,
    val brand : String,
    val category : String,
    val imageUrl : String,
)

data class ProductResponse(
    val dto : Product,
    val list : List<Product>,
    val status : String
)
