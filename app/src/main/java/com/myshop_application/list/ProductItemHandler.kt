package com.myshop_application.list

import com.myshop_application.model.Product

interface ProductItemHandler {

    fun onClickItem(item : Product)
}