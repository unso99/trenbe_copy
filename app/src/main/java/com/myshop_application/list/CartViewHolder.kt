package com.myshop_application.list

import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import com.myshop_application.databinding.ItemCartBinding
import com.myshop_application.model.Cart
import com.myshop_application.repository.ImageProvider
import com.myshop_application.util.PreferenceUtil

class CartViewHolder(
    private val binding: ItemCartBinding,
    private val token : String,
    private val handler: CartItemHandler? = null
) :
    RecyclerView.ViewHolder(binding.root), ImageProvider.Callback {

    fun bind(item: Cart) {
        binding.item = item
        binding.handler = handler
        ImageProvider(this).getImage(token,item.product_imageUrl!!)
    }

    override fun getImage(bitmap: Bitmap) {
        binding.imageView.setImageBitmap(bitmap)
    }
}