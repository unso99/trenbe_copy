package com.myshop_application.list

import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import com.myshop_application.databinding.ItemOrderBinding
import com.myshop_application.model.Cart
import com.myshop_application.repository.ImageProvider

class OrderViewHolder(
    private val binding: ItemOrderBinding,
    private val token : String
) :
    RecyclerView.ViewHolder(binding.root), ImageProvider.Callback {

    fun bind(item: Cart) {
        binding.item = item
        ImageProvider(this).getImage(token,item.product_imageUrl!!)
    }

    override fun getImage(bitmap: Bitmap) {
        binding.imageView.setImageBitmap(bitmap)
    }
}