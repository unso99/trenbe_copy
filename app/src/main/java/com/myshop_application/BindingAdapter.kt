package com.myshop_application

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

@BindingAdapter("money")
fun TextView.money(money: Long) {
    text = DecimalFormat("#,###").format(money) + "원"
}
