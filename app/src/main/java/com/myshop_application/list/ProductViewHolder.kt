package com.myshop_application.list

import android.content.Intent
import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import com.myshop_application.databinding.ItemProdBinding
import com.myshop_application.model.Product
import com.myshop_application.repository.ImageProvider

class ProductViewHolder(
    private val binding: ItemProdBinding,
    private val token : String,
    private val handler : ProductItemHandler? = null
    ) :
    RecyclerView.ViewHolder(binding.root), ImageProvider.Callback {

    fun bind(item: Product) {
        binding.item = item
        binding.handler = handler
        ImageProvider(this).getImage(token,item.imageUrl!!)
    }

    override fun getImage(bitmap: Bitmap) {
        binding.imageView.setImageBitmap(bitmap)
    }
}
