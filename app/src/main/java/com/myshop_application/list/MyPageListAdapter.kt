package com.myshop_application.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myshop_application.databinding.ItemMypageBinding
import com.myshop_application.model.Common

class MyPageListAdapter : ListAdapter<Common, MyPageViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Common>() {
            override fun areItemsTheSame(oldItem: Common, newItem: Common): Boolean {
                return oldItem.code_id == newItem.code_id
            }

            override fun areContentsTheSame(oldItem: Common, newItem: Common): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyPageViewHolder(ItemMypageBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}