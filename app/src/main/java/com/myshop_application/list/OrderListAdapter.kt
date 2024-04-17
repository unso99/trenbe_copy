package com.myshop_application.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myshop_application.databinding.ItemCartBinding
import com.myshop_application.databinding.ItemOrderBinding
import com.myshop_application.model.Cart
import com.myshop_application.util.PreferenceUtil

class OrderListAdapter() :
    ListAdapter<Cart, OrderViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Cart>() {
            override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val token = PreferenceUtil(parent.context).getToken("token")!!
        return OrderViewHolder(ItemOrderBinding.inflate(inflater, parent, false), token)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}