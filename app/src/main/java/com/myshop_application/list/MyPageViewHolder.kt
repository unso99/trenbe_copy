package com.myshop_application.list

import androidx.recyclerview.widget.RecyclerView
import com.myshop_application.databinding.ItemMypageBinding
import com.myshop_application.model.Common

class MyPageViewHolder(private val binding: ItemMypageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Common) {
        binding.item = item
    }
}