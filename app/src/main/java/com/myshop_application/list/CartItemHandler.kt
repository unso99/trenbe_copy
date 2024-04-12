package com.myshop_application.list

import com.myshop_application.model.Cart

interface CartItemHandler {

    fun onDeleteItem(item: Cart)
}