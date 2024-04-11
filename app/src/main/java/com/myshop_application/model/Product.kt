package com.myshop_application.model

import java.io.Serializable

data class Product(
    val id : Long? = null,
    val name : String? = null,
    val price : Long? = null,
    val brand : String? = null,
    val category : String? = null,
    val imageUrl : String? = null,
) : Serializable

data class ProductResponse(
    val dto : Product,
    val list : List<Product>,
    val status : String
)
