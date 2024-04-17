package com.myshop_application.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myshop_application.databinding.ItemProdBinding
import com.myshop_application.model.Product
import com.myshop_application.util.PreferenceUtil

class ProductListAdapter(private val itemHandler: ProductItemHandler? = null) :
    ListAdapter<Product, ProductViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val token = PreferenceUtil(parent.context).getToken("token")!!
        return ProductViewHolder(
            ItemProdBinding.inflate(inflater, parent, false),
            token,
            itemHandler
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

        }
    }
}